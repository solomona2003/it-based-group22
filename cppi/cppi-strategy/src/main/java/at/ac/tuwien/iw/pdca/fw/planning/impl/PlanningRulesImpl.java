package at.ac.tuwien.iw.pdca.fw.planning.impl;

import at.ac.tuwien.imw.pdca.fw.PlanConfiguration;
import at.ac.tuwien.imw.pdca.fw.PlanningRules;

public class PlanningRulesImpl implements PlanningRules<PlanConfiguration>{
	
	// -- Rules --
	
	private final static double R = 0.05;
	
	private final static int D = 365;
	
	private final static double T_0T = 1;
	
	//-- --
	
	private PlanConfigurationImpl planConfiguration;
	
	public PlanningRulesImpl(PlanConfigurationImpl planConfiguration) {
		this.planConfiguration = planConfiguration;
	}
	
	@Override
	public PlanConfiguration applyPlanningRules() {
		planConfiguration.setD(D);
		planConfiguration.setR(R);
		planConfiguration.setT_0T(T_0T);
		return planConfiguration;
	}
}
