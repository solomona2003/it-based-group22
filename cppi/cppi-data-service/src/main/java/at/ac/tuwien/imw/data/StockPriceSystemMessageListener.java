package at.ac.tuwien.imw.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.imw.data.db.queries.PlanConfigurationRepository;
import at.ac.tuwien.imw.data.db.queries.SimulationPeriodValuesRepository;
import at.ac.tuwien.imw.data.dbmodel.PlanConfiguration;
import at.ac.tuwien.imw.data.dbmodel.SimulationPeriodValues;
import at.ac.tuwien.imw.data.dto.StockPriceDTO;
import at.ac.tuwien.imw.stock.price.Stock;

@Service
public class StockPriceSystemMessageListener
{
	private static final Logger LOG = LoggerFactory.getLogger( StockPriceSystemMessageListener.class );

	public static final String STOCK_PRICE_UPDATE_QUEUE = "stockPriceUpdateQueue";

	@Autowired
	private PlanConfigurationRepository planConfigurationRepository;

	@Autowired
	private SimulationPeriodValuesRepository simulationPeriodValuesRepository;

	@Autowired
	private CPPIStrategyMessageSender cppiStrategyMessageSender;

	@Autowired
	public StockPriceSystemMessageListener( final RabbitAdmin admin )
	{
		admin.purgeQueue( STOCK_PRICE_UPDATE_QUEUE, false );
	}

	@RabbitListener( queues = STOCK_PRICE_UPDATE_QUEUE )
	public void onStockPriceUpdate( final Stock stock )
	{
		LOG.info( "Received stock price " + stock );
		for ( final PlanConfiguration planConfiguration : this.planConfigurationRepository.findAll() )
		{
			if ( planConfiguration.getT() >= planConfiguration.getSimulationPeriodValues().size() )
			{
				final int lastIndex = planConfiguration.getSimulationPeriodValues().size() - 1;
				StockPriceDTO stockPriceDTO = null;
				int t = -1;
				if ( lastIndex < 0 )
				{
					t = 0;
					stockPriceDTO = new StockPriceDTO( stock.getPrice(), null, t, planConfiguration.getId(),
							planConfiguration.getT_0T(), planConfiguration.getD() );
				}
				else
				{
					t = planConfiguration.getSimulationPeriodValues().get( lastIndex ).getT() + 1;
					final int previousStockPrice =
							planConfiguration.getSimulationPeriodValues().get( lastIndex ).getS_t();
					stockPriceDTO = new StockPriceDTO( stock.getPrice(), previousStockPrice, t,
							planConfiguration.getId(), planConfiguration.getT_0T(), planConfiguration.getD() );
				}

				final SimulationPeriodValues periodValues = new SimulationPeriodValues();
				periodValues.setS_t( stock.getPrice() );
				periodValues.setT( t );
				planConfiguration.getSimulationPeriodValues().add( periodValues );
				this.simulationPeriodValuesRepository.save( periodValues );
				this.planConfigurationRepository.save( planConfiguration );
				this.cppiStrategyMessageSender.sendMeasureMessage( stockPriceDTO );
			}
		}
	}
}
