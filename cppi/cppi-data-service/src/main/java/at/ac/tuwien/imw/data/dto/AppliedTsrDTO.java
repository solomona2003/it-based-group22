package at.ac.tuwien.imw.data.dto;

public class AppliedTsrDTO
{
	private Double tsr;

	private long configurationId;

	private double t_tT;

	public AppliedTsrDTO()
	{
	}

	public AppliedTsrDTO( final Double tsr, final double t_tT, final long configurationId )
	{
		this.tsr = tsr;
		this.t_tT = t_tT;
		this.configurationId = configurationId;
	}

	public Double getTsr()
	{
		return this.tsr;
	}

	public long getConfigurationId()
	{
		return this.configurationId;
	}

	public double getT_tT()
	{
		return this.t_tT;
	}

	@Override
	public String toString()
	{
		return "AppliedTsrDTO [tsr=" + this.tsr + ", configurationId=" + this.configurationId + ", t_tT=" + this.t_tT
				+ "]";
	}
}
