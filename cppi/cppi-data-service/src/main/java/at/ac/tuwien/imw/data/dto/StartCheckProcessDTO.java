package at.ac.tuwien.imw.data.dto;

public class StartCheckProcessDTO
{
	private Double tsr;

	private double r_T;

	private double f_T;

	private Double t_tT;

	private Double x_rtm1;

	private Double x_ftm1;

	private double d;

	private long configurationId;

	private double w_0;

	public StartCheckProcessDTO()
	{
	}

	public StartCheckProcessDTO( final Double tsr, final double r_T, final double f_T, final Double t_tT,
			final Double x_rtm1, final Double x_ftm1, final double d, final double w_0, final long configurationId )
	{
		this.tsr = tsr;
		this.r_T = r_T;
		this.f_T = f_T;
		this.t_tT = t_tT;
		this.x_rtm1 = x_rtm1;
		this.x_ftm1 = x_ftm1;
		this.d = d;
		this.w_0 = w_0;
		this.configurationId = configurationId;
	}

	public Double getTsr()
	{
		return this.tsr;
	}

	public double getR_T()
	{
		return this.r_T;
	}

	public double getF_T()
	{
		return this.f_T;
	}

	public Double getT_tT()
	{
		return this.t_tT;
	}

	public Double getX_rtm1()
	{
		return this.x_rtm1;
	}

	public Double getX_ftm1()
	{
		return this.x_ftm1;
	}

	public double getD()
	{
		return this.d;
	}

	public long getConfigurationId()
	{
		return this.configurationId;
	}

	public double getW_0()
	{
		return this.w_0;
	}

	@Override
	public String toString()
	{
		return "StartCheckProcessDTO [tsr=" + this.tsr + ", r_T=" + this.r_T + ", f_T=" + this.f_T + ", t_tT="
				+ this.t_tT + ", X_rtm1=" + this.x_rtm1 + ", X_ftm1=" + this.x_ftm1 + ", d=" + this.d
				+ ", configurationId=" + this.configurationId + ", w_0=" + this.w_0 + "]";
	}
}
