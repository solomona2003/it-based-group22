package at.ac.tuwien.imw.data.dto;

public class StartActProcessDTO
{
	private double m;

	private double c_t;

	private double b;

	private double w_t;

	private long configurationId;

	public StartActProcessDTO()
	{
	}

	public StartActProcessDTO( final double m, final double c_t, final double b, final double w_t,
			final long configurationId )
	{
		this.m = m;
		this.c_t = c_t;
		this.b = b;
		this.w_t = w_t;
		this.configurationId = configurationId;
	}

	public double getM()
	{
		return this.m;
	}

	public double getC_t()
	{
		return this.c_t;
	}

	public double getB()
	{
		return this.b;
	}

	public double getW_t()
	{
		return this.w_t;
	}

	public long getConfigurationId()
	{
		return this.configurationId;
	}

	@Override
	public String toString()
	{
		return "StartActProcessDTO [m=" + this.m + ", c_t=" + this.c_t + ", b=" + this.b + ", W_t=" + this.w_t
				+ ", configurationId=" + this.configurationId + "]";
	}
}
