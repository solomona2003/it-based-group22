package at.ac.tuwien.imw.data.dbmodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents one row in the relational DB, containing values calculated through
 * the CPPI algorithm for one period t.
 */
@Entity
public class SimulationPeriodValues
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

	// time period
	private int t;

	private Double T_tT;

	// floor value
	private Double F_t;

	// cushion
	private Double C_t;

	// risky exposure
	private Double X_rt;

	// riskfree exposure
	private Double X_ft;

	// stock price
	private int S_t;

	// total shareholder return
	private Double TSR_t;

	// investor's return
	private Double W_t;

	public Long getId()
	{
		return this.id;
	}

	public void setId( final Long id )
	{
		this.id = id;
	}

	public int getT()
	{
		return this.t;
	}

	public void setT( final int t )
	{
		this.t = t;
	}

	public Double getT_tT()
	{
		return this.T_tT;
	}

	public void setT_tT( final Double t_tT )
	{
		this.T_tT = t_tT;
	}

	public Double getF_t()
	{
		return this.F_t;
	}

	public void setF_t( final Double f_t )
	{
		this.F_t = f_t;
	}

	public Double getC_t()
	{
		return this.C_t;
	}

	public void setC_t( final Double c_t )
	{
		this.C_t = c_t;
	}

	public Double getX_rt()
	{
		return this.X_rt;
	}

	public void setX_rt( final Double x_rt )
	{
		this.X_rt = x_rt;
	}

	public Double getX_ft()
	{
		return this.X_ft;
	}

	public void setX_ft( final Double x_ft )
	{
		this.X_ft = x_ft;
	}

	public int getS_t()
	{
		return this.S_t;
	}

	public void setS_t( final int s_t )
	{
		this.S_t = s_t;
	}

	public Double getTSR_t()
	{
		return this.TSR_t;
	}

	public void setTSR_t( final Double tSR_t )
	{
		this.TSR_t = tSR_t;
	}

	public Double getW_t()
	{
		return this.W_t;
	}

	public void setW_t( final Double w_t )
	{
		this.W_t = w_t;
	}

	@Override
	public String toString()
	{
		return "SimulationPeriod [id=" + this.id + ", t=" + this.t + ", T_tT=" + this.T_tT + ", F_t=" + this.F_t
				+ ", C_t=" + this.C_t + ", X_rt=" + this.X_rt + ", X_ft=" + this.X_ft + ", S_t=" + this.S_t + ", TSR_t="
				+ this.TSR_t + ", W_t=" + this.W_t + "]";
	}
}
