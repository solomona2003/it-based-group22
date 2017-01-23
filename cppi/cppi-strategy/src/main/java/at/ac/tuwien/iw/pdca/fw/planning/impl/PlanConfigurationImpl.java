package at.ac.tuwien.iw.pdca.fw.planning.impl;

import at.ac.tuwien.imw.data.dto.UserConfigurationDTO;
import at.ac.tuwien.imw.pdca.fw.PlanConfiguration;

public class PlanConfigurationImpl extends PlanConfiguration
{
	private double R; // set by PlanningRules

	private int d; // set by PlanningRules

	private double T_0T; // set by PlanningRules

	private UserConfigurationDTO userConfiguration;

	public PlanConfigurationImpl()
	{
		// dummy constructor for JSON deserialization
	}

	public PlanConfigurationImpl( final UserConfigurationDTO userConfiguration )
	{
		super.id = userConfiguration.getId();
		this.userConfiguration = userConfiguration;
	}

	public UserConfigurationDTO getUserConfiguration()
	{
		return this.userConfiguration;
	}

	public void setR(final double r)
	{
		this.R = r;
	}

	public void setD(final int d)
	{
		this.d = d;
	}

	public void setT_0T(final double t_0t)
	{
		this.T_0T = t_0t;
	}

	public double getR()
	{
		return this.R;
	}

	public int getD()
	{
		return this.d;
	}

	public double getT_0T()
	{
		return this.T_0T;
	}
}
