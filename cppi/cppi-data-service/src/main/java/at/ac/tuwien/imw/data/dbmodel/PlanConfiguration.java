package at.ac.tuwien.imw.data.dbmodel;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents one plan configuration row in the relational DB.
 */
@Entity
public class PlanConfiguration
{
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private Long id;

	@OneToMany( fetch = FetchType.EAGER )
	private List<SimulationPeriodValues> simulationPeriodValues;

	// floor value
	private double F_T;

	// multiplier
	private double m;

	// max. risky fraction
	private double b;

	// initial investment value
	private double W_0;

	// time horizon of portfolio investments
	private int T;

	private Double R_t;

	private Double d;

	private Double T_0T;

	private final Instant timeStamp = Instant.now();

	public Long getId()
	{
		return this.id;
	}

	public void setId( final Long id )
	{
		this.id = id;
	}

	public double getF_T()
	{
		return this.F_T;
	}

	public void setF_T( final double f_T )
	{
		this.F_T = f_T;
	}

	public double getM()
	{
		return this.m;
	}

	public void setM( final double m )
	{
		this.m = m;
	}

	public double getB()
	{
		return this.b;
	}

	public void setB( final double b )
	{
		this.b = b;
	}

	public double getW_0()
	{
		return this.W_0;
	}

	public void setW_0( final double w_0 )
	{
		this.W_0 = w_0;
	}

	public int getT()
	{
		return this.T;
	}

	public void setT( final int t )
	{
		this.T = t;
	}

	public Double getR_t()
	{
		return this.R_t;
	}

	public void setR_t( final Double r_t )
	{
		this.R_t = r_t;
	}

	public Double getD()
	{
		return this.d;
	}

	public void setD( final Double d )
	{
		this.d = d;
	}

	public Double getT_0T()
	{
		return this.T_0T;
	}

	public void setT_0T( final Double t_0t )
	{
		this.T_0T = t_0t;
	}

	@JsonProperty
	public String getTimeStamp()
	{
		return ZonedDateTime.ofInstant( this.timeStamp, ZoneId.systemDefault() ).toString();
	}

	public List<SimulationPeriodValues> getSimulationPeriodValues()
	{
		return this.simulationPeriodValues;
	}

	@Override
	public String toString()
	{
		return "PlanConfiguration [id=" + this.id + ", simulationPeriodValues=" + this.simulationPeriodValues + ", F_T="
				+ this.F_T + ", m=" + this.m + ", b=" + this.b + ", W_0=" + this.W_0 + ", T=" + this.T + ", R_t="
				+ this.R_t + ", d=" + this.d + ", T_0T=" + this.T_0T + "]";
	}
}
