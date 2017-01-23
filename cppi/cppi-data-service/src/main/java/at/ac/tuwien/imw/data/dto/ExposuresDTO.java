package at.ac.tuwien.imw.data.dto;

/**
 * Contains the to be invested exposure values.
 */
public class ExposuresDTO
{
	private Long configurationId;

	private double x_rt;

	private double x_ft;

	public ExposuresDTO()
	{
	}

	public ExposuresDTO( final Long configurationId, final double x_rt, final double x_ft )
	{
		this.configurationId = configurationId;
		this.x_rt = x_rt;
		this.x_ft = x_ft;
	}

	public Long getConfigurationId()
	{
		return this.configurationId;
	}

	public double getX_rt()
	{
		return this.x_rt;
	}

	public double getX_ft()
	{
		return this.x_ft;
	}

	@Override
	public String toString()
	{
		return "ExposuresDTO [configurationId=" + this.configurationId + ", X_rt=" + this.x_rt + ", X_ft=" + this.x_ft
				+ "]";
	}
}
