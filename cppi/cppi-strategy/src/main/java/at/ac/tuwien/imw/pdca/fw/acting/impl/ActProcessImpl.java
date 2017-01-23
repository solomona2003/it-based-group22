package at.ac.tuwien.imw.pdca.fw.acting.impl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import at.ac.tuwien.imw.data.CPPIStrategyRestController;
import at.ac.tuwien.imw.data.dto.ExposuresDTO;
import at.ac.tuwien.imw.pdca.fw.ActProcess;
import at.ac.tuwien.imw.pdca.fw.CorrectiveActOutput;
import at.ac.tuwien.imw.pdca.fw.Deviation;

public class ActProcessImpl extends ActProcess<ExposuresDTO, Double>
{

	private static final Logger LOG = LoggerFactory.getLogger( ActProcessImpl.class );

	private final CorrectiveActRulesImpl correctiveActRules;

	public ActProcessImpl( final CorrectiveActRulesImpl correctiveActRules )
	{
		this.correctiveActRules = correctiveActRules;
	}

	@Override
	public CorrectiveActOutput<ExposuresDTO> act(final Deviation<Double> deviation)
	{
		this.correctiveActRules.applyActRules();
		final ExposuresDTO exposuresDTO = new ExposuresDTO( this.correctiveActRules.getConfigurationId(),
				this.correctiveActRules.getX_rt(), this.correctiveActRules.getX_ft() );
		final CorrectiveActOutput<ExposuresDTO> output = new CorrectiveActOutputImpl( exposuresDTO );
		LOG.info( "exposure before sending: " + exposuresDTO );
		setExposures( exposuresDTO );
		return output;
	}

	private void setExposures(final ExposuresDTO exposures)
	{
		final RestTemplate restTemplate = new RestTemplate();
		final URI url = URI.create( CPPIStrategyRestController.SET_EXPOSURES_URL );
		restTemplate.postForObject( url, exposures, Void.class );
	}
}
