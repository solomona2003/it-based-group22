package at.ac.tuwien.iw.pdca.fw.planning.impl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import at.ac.tuwien.imw.data.CPPIStrategyRestController;
import at.ac.tuwien.imw.data.dto.AppliedPlanConfigurationDTO;
import at.ac.tuwien.imw.pdca.fw.PlanProcess;
import at.ac.tuwien.imw.pdca.fw.PlanningRules;

public class PlanProcessImpl extends PlanProcess<PlanConfigurationImpl>
{
	private static final Logger LOG = LoggerFactory.getLogger( PlanProcessImpl.class );

	public PlanProcessImpl( final PlanningRules<PlanConfigurationImpl> planningRules )
	{
		this.planningRules = planningRules;
	}

	@Override
	public void plan()
	{
		LOG.info( "Applying planning rules." );
		final PlanConfigurationImpl pc = this.planningRules.applyPlanningRules();
		final AppliedPlanConfigurationDTO pcDTO =
				new AppliedPlanConfigurationDTO( pc.getUserConfiguration(), pc.getR(), pc.getD(), pc.getT_0T() );
		setPlanConfiguration( pcDTO );
		LOG.info( "Sent applied plan configuration to data service." );
	}

	private void setPlanConfiguration(final AppliedPlanConfigurationDTO planConfiguration)
	{
		final RestTemplate restTemplate = new RestTemplate();
		final URI url = URI.create( CPPIStrategyRestController.SET_CONFIGURATION_URL );
		restTemplate.postForObject( url, planConfiguration, Void.class );
	}
}
