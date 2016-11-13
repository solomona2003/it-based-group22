package at.ac.tuwien.imw.pdca.fw;



public abstract class DoProcess implements Runnable {
	
	protected DoRules doRules;
	
	public abstract void operate();

}
