package at.ac.tuwien.iw.pdca.fw.planning.impl;

import at.ac.tuwien.imw.cppi.strategy.DummyDataService;
import at.ac.tuwien.imw.pdca.fw.PlanConfiguration;
import at.ac.tuwien.imw.pdca.fw.PlanProcess;
import at.ac.tuwien.imw.pdca.fw.PlanningRules;

public class PlanProcessImpl extends PlanProcess<PlanConfiguration> {

	public PlanProcessImpl(PlanningRules<PlanConfiguration> planningRules) {
		this.planningRules = planningRules;
	}
	
	@Override
	public void run() {
		plan();
	}

	@Override
	public void plan() {
		PlanConfiguration pc = planningRules.applyPlanningRules();
		DummyDataService.setPlanConfiguration(pc, DummyDataService.CID);
	}
}
