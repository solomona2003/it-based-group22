package at.ac.tuwien.imw.data.dto;

/**
 * Contains the user configuration as handed over by the user via frontend. Does
 * not contain values applied through plan rules.
 */
public class UserConfigurationDTO
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

	public UserConfigurationDTO()
	{
	}

	public UserConfigurationDTO( final Long id, final double f_T, final double m, final double b, final double w_0,
			final int t )
	{
		this.id = id;
		this.f_T = f_T;
		this.m = m;
		this.b = b;
		this.w_0 = w_0;
		this.t = t;
	}

	public void setId( final Long id )
	{
		this.id = id;
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

	@Override
	public String toString()
	{
		return "UserConfigurationDTO [id=" + this.id + ", F_T=" + this.f_T + ", m=" + this.m + ", b=" + this.b
				+ ", W_0=" + this.w_0 + ", T=" + this.t + "]";
	}
}
