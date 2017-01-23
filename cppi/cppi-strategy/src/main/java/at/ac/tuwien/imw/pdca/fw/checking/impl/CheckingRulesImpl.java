package at.ac.tuwien.imw.pdca.fw.checking.impl;

import at.ac.tuwien.imw.data.dto.StartCheckProcessDTO;
import at.ac.tuwien.imw.pdca.fw.CheckingRules;

public class CheckingRulesImpl implements CheckingRules
{
	// C_t
	private Double deviation;

	// F_t
	private Double objectiveSetting;

	// W_t
	private Double performance;

	private final StartCheckProcessDTO checkProcessDTO;

	public CheckingRulesImpl( final StartCheckProcessDTO checkProcessDTO )
	{
		this.checkProcessDTO = checkProcessDTO;
	}

	@Override
	public void applyCheckingRules()
	{
		calculateF_t();
		calculateW_t();
		calculateC_t();
	}

	private void calculateW_t()
	{
		if ( this.checkProcessDTO.getTsr() != null )
		{
			this.performance = this.checkProcessDTO.getX_rtm1() * ( 1 + this.checkProcessDTO.getTsr() )
					+ this.checkProcessDTO.getX_ftm1()
							* Math.pow( 1 + this.checkProcessDTO.getR_T(), 1 / this.checkProcessDTO.getD() );
		}
		else
		{
			this.performance = this.checkProcessDTO.getW_0();
		}
	}

	private void calculateF_t()
	{
		this.objectiveSetting = this.checkProcessDTO.getF_T()
				/ Math.pow( 1 + this.checkProcessDTO.getR_T(), this.checkProcessDTO.getT_tT() );
	}

	private void calculateC_t()
	{
		this.deviation = Math.max( this.performance - this.objectiveSetting, 0 );
	}

	public Double getDeviation()
	{
		return this.deviation;
	}

	public Double getF_t()
	{
		return this.objectiveSetting;
	}

	public Double getW_t()
	{
		return this.performance;
	}

	public long getConfigurationId()
	{
		return this.checkProcessDTO.getConfigurationId();
	}
}
