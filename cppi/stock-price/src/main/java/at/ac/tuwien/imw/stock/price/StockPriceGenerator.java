package at.ac.tuwien.imw.stock.price;

import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * the generator has an array of prices, 25 of them, then it takes the user
 * period specification as input and with the {@link ScheduledExecutorService}
 * it makes stock objects by passing in the price from the array
 *
 * at the stock, the stock makes the stock object with the price passed from the
 * StockPriceGenerator and stamps the time at creation
 *
 */
@Service
public class StockPriceGenerator
{
	private static final Logger LOG = LoggerFactory.getLogger( StockPriceGenerator.class );

	private int t;

	private static int[] PRICES = { 100, 102, 105, 110, 115, 115, 115, 117, 120, 119, 116, 116, 116, 114, 118, 120, 125,
			130, 123, 119, 116, 115, 114, 113, 120 };

	// used to convert and send message
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public StockPriceGenerator()
	{
		this.t = 0;
	}

	@Scheduled( fixedRate = 3000 )
	public void schedule()
	{
		final int latestPrice = PRICES[StockPriceGenerator.this.t];
		final Stock stock = new Stock( latestPrice );
		LOG.info( "sending stock " + stock );
		StockPriceGenerator.this.t++;
		if ( this.t == PRICES.length )
		{
			this.t = 0;
		}
		StockPriceGenerator.this.rabbitTemplate.convertAndSend( StockPriceApplication.STOCK_PRICE_UPDATE_QUEUE, stock );
	}
}
