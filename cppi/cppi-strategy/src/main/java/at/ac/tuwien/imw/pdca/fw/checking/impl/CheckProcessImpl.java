package at.ac.tuwien.imw.pdca.fw.checking.impl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import at.ac.tuwien.imw.data.CPPIStrategyRestController;
import at.ac.tuwien.imw.data.dto.AppliedCheckDTO;
import at.ac.tuwien.imw.pdca.fw.CheckProcess;
import at.ac.tuwien.imw.pdca.fw.Deviation;
import at.ac.tuwien.imw.pdca.fw.MeasuredPerformanceValue;
import at.ac.tuwien.imw.pdca.fw.ObjectiveSetting;

public class CheckProcessImpl extends CheckProcess<Double>
{

	private static final Logger LOG = LoggerFactory.getLogger( CheckProcessImpl.class );

	private final CheckingRulesImpl checkingRules;

	public CheckProcessImpl( final CheckingRulesImpl checkingRules )
	{
		this.checkingRules = checkingRules;
	}

	@Override
	public Deviation<Double> getCheckResult(
			final ObjectiveSetting<Double> objective, final MeasuredPerformanceValue<Double> performanceMeasureValue)
	{
		this.checkingRules.applyCheckingRules();
		final DeviationImpl deviation = new DeviationImpl( this.checkingRules.getDeviation() );
		LOG.info( "applied check dto: " + new AppliedCheckDTO( this.checkingRules.getDeviation(),
				this.checkingRules.getF_t(), this.checkingRules.getW_t(), this.checkingRules.getConfigurationId() ) );
		setAppliedCheckDTO( new AppliedCheckDTO( this.checkingRules.getDeviation(), this.checkingRules.getF_t(),
				this.checkingRules.getW_t(), this.checkingRules.getConfigurationId() ) );
		return deviation;
	}

	private void setAppliedCheckDTO(final AppliedCheckDTO appliedCheckDTO)
	{
		final RestTemplate restTemplate = new RestTemplate();
		final URI url = URI.create( CPPIStrategyRestController.SET_APPLIED_CHECK_URL );
		restTemplate.postForObject( url, appliedCheckDTO, Void.class );
	}
}
