package at.ac.tuwien.imw.pdca.fw;



public abstract class PlanProcess<T> implements Runnable {
	
	protected PlanningRules<T> planningRules;
	
	public abstract void plan();

}
