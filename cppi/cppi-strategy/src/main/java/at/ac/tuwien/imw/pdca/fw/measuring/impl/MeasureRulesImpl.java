package at.ac.tuwien.imw.pdca.fw.measuring.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.tuwien.imw.data.dto.StockPriceDTO;
import at.ac.tuwien.imw.pdca.fw.MeasureRules;
import at.ac.tuwien.imw.pdca.fw.MeasuredPerformanceValue;

public class MeasureRulesImpl implements MeasureRules<Double>
{
	private final StockPriceDTO stockPrice;

	private static final Logger LOG = LoggerFactory.getLogger( MeasureRulesImpl.class );

	public MeasureRulesImpl( final StockPriceDTO stockPrice )
	{
		this.stockPrice = stockPrice;
	}

	@Override
	public MeasuredPerformanceValue<Double> measure()
	{
		// S_t / S_(t-1) -1
		Double calculatedTSRValue = null;
		if ( this.stockPrice.getStockPricePrevious() != null )
		{
			final double ratio = ( double ) this.stockPrice.getStockPriceCurrent()
					/ ( double ) this.stockPrice.getStockPricePrevious();
			LOG.info( "calculated ratio: " + ratio );
			calculatedTSRValue = ratio - 1;
		}
		LOG.info( "calculatedValue: " + calculatedTSRValue );
		final TSR tsr = new TSR( calculatedTSRValue );
		LOG.info( "outter tsr: " + tsr );
		return tsr;
	}

	public Double getT_tT()
	{
		return this.stockPrice.getT_0T() - this.stockPrice.getCurrentPeriod() / this.stockPrice.getD();
	}

	public Long getConfigurationId()
	{
		return this.stockPrice.getConfigurationId();
	}
}
