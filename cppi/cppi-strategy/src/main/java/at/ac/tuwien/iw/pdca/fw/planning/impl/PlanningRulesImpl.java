package at.ac.tuwien.iw.pdca.fw.planning.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.tuwien.imw.pdca.fw.PlanningRules;

public class PlanningRulesImpl implements PlanningRules<PlanConfigurationImpl>
{

	private static final Logger LOG = LoggerFactory.getLogger( PlanningRulesImpl.class );

	// -- Rules --

	private final static double R = 0.05;

	private final static int D = 365;

	private final static double T_0T = 1;

	// -- --

	private final PlanConfigurationImpl planConfiguration;

	public PlanningRulesImpl( final PlanConfigurationImpl planConfiguration )
	{
		this.planConfiguration = planConfiguration;
	}

	@Override
	public PlanConfigurationImpl applyPlanningRules()
	{
		this.planConfiguration.setD( D );
		this.planConfiguration.setR( R );
		this.planConfiguration.setT_0T( T_0T );
		LOG.info( "Applied planning rules to user configuration." );
		return this.planConfiguration;
	}
}
