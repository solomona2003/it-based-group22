package at.ac.tuwien.imw.pdca.fw.measuring.impl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import at.ac.tuwien.imw.data.CPPIStrategyRestController;
import at.ac.tuwien.imw.data.dto.AppliedTsrDTO;
import at.ac.tuwien.imw.pdca.fw.MeasuredPerformanceValue;

//triggered by StockPriceSystem
public class MeasureProcess
{

	private static final Logger LOG = LoggerFactory.getLogger( MeasureProcess.class );

	private final MeasureRulesImpl measureRules;

	public MeasureProcess( final MeasureRulesImpl measureRulesImpl )
	{
		this.measureRules = measureRulesImpl;
	}

	public void updateStockPrice()
	{
		final MeasuredPerformanceValue<Double> tsrPerformanceValue = this.measureRules.measure();
		final Double tsr = tsrPerformanceValue.getValue();
		LOG.info( "tsr: " + tsr );
		setTSR( new AppliedTsrDTO( tsr, this.measureRules.getT_tT(), this.measureRules.getConfigurationId() ) );
	}

	public void setTSR(final AppliedTsrDTO appliedTsrDTO)
	{
		final RestTemplate restTemplate = new RestTemplate();
		final URI url = URI.create( CPPIStrategyRestController.SET_TSR_URL );
		restTemplate.postForObject( url, appliedTsrDTO, Void.class );
	}
}
