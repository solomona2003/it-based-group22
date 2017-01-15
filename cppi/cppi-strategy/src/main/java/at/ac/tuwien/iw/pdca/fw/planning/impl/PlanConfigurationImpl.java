package at.ac.tuwien.iw.pdca.fw.planning.impl;

import at.ac.tuwien.imw.pdca.fw.PlanConfiguration;

public class PlanConfigurationImpl extends PlanConfiguration {
	private double R; //set by PlanningRules
	
	private int D; //set by PlanningRules
	
	private double T_0T; //set by PlanningRules
	
	
	
	private int F_T; //floor value
	
	private int m; //multiplier
	
	private double b; //max. risky fraction
	
	private double W_0; //initial investment value
		
	private int T; //time horizon of portfolio investments

	public PlanConfigurationImpl(Long id, int F_T, int m, double b, double W_0, int T) {
		super.id = id;
		this.F_T = F_T;
		this.m = m;
		this.b = b;
		this.W_0 = W_0;
		this.T = T;
	}

	public int getF_T() {
		return F_T;
	}

	public int getM() {
		return m;
	}

	public double getB() {
		return b;
	}

	public double getW_0() {
		return W_0;
	}

	public int getT() {
		return T;
	}

	public void setR(double r) {
		R = r;
	}

	public void setD(int d) {
		D = d;
	}

	public void setT_0T(double t_0t) {
		T_0T = t_0t;
	}

	public double getR() {
		return R;
	}

	public int getD() {
		return D;
	}

	public double getT_0T() {
		return T_0T;
	}
}
