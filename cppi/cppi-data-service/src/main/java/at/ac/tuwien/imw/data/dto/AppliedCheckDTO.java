package at.ac.tuwien.imw.data.dto;

public class AppliedCheckDTO
{
	private double c_t;

	private double f_t;

	private double w_t;

	private long configurationId;

	public AppliedCheckDTO()
	{
	}

	public AppliedCheckDTO( final double c_t, final double f_t, final double w_t, final long configurationId )
	{
		this.c_t = c_t;
		this.f_t = f_t;
		this.w_t = w_t;
		this.configurationId = configurationId;
	}

	public double getC_t()
	{
		return this.c_t;
	}

	public double getF_t()
	{
		return this.f_t;
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
		return "AppliedCheckDTO [c_t=" + this.c_t + ", f_t=" + this.f_t + ", w_t=" + this.w_t + ", configurationId="
				+ this.configurationId + "]";
	}

}
