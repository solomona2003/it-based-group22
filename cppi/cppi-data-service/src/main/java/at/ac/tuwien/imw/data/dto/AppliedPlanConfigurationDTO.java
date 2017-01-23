package at.ac.tuwien.imw.data.dto;

/**
 * Contains all values from the plan configuration (user values and through
 * applying plan rules).
 */
public class AppliedPlanConfigurationDTO
{
	private Long id;

	// floor value
	private double f_T;

	// multiplier
	private double m;

	// max. risky fraction
	private double b;

	// initial investment value
	private double w_0;

	// time horizon of portfolio investments
	private int t;

	private double r_t;

	private double d;

	private double t_0T;

	public AppliedPlanConfigurationDTO()
	{
		// for JSON deserialization
	}

	public AppliedPlanConfigurationDTO( final UserConfigurationDTO userConfiguration, final double R_t, final double d,
			final double T_0t )
	{
		this.id = userConfiguration.getId();
		this.f_T = userConfiguration.getF_T();
		this.m = userConfiguration.getM();
		this.b = userConfiguration.getB();
		this.w_0 = userConfiguration.getW_0();
		this.t = userConfiguration.getT();
		this.r_t = R_t;
		this.d = d;
		this.t_0T = T_0t;
	}

	public Long getId()
	{
		return this.id;
	}

	public double getF_T()
	{
		return this.f_T;
	}

	public double getM()
	{
		return this.m;
	}

	public double getB()
	{
		return this.b;
	}

	public double getW_0()
	{
		return this.w_0;
	}

	public int getT()
	{
		return this.t;
	}

	public double getR_t()
	{
		return this.r_t;
	}

	public double getD()
	{
		return this.d;
	}

	public double getT_0T()
	{
		return this.t_0T;
	}

	@Override
	public String toString()
	{
		return "AppliedPlanConfigurationDTO [id=" + this.id + ", F_T=" + this.f_T + ", m=" + this.m + ", b=" + this.b
				+ ", W_0=" + this.w_0 + ", T=" + this.t + ", R_t=" + this.r_t + ", d=" + this.d + ", T_0T=" + this.t_0T
				+ "]";
	}
}
