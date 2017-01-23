package at.ac.tuwien.imw.pdca.fw.acting.impl;

import at.ac.tuwien.imw.data.dto.StartActProcessDTO;
import at.ac.tuwien.imw.pdca.fw.CorrectiveActRules;

public class CorrectiveActRulesImpl implements CorrectiveActRules
{
	private final StartActProcessDTO startActProcessDTO;

	private Double X_rt;

	private Double X_ft;

	public CorrectiveActRulesImpl( final StartActProcessDTO startActProcessDTO )
	{
		this.startActProcessDTO = startActProcessDTO;
	}

	@Override
	public void applyActRules()
	{
		calculateX_rt();
		calculateX_ft();
	}

	public Double getX_rt()
	{
		return this.X_rt;
	}

	public Double getX_ft()
	{
		return this.X_ft;
	}

	public long getConfigurationId()
	{
		return this.startActProcessDTO.getConfigurationId();
	}

	private void calculateX_rt()
	{
		this.X_rt = Math.min( this.startActProcessDTO.getM() * this.startActProcessDTO.getC_t(),
				this.startActProcessDTO.getB() * this.startActProcessDTO.getW_t() );
	}

	private void calculateX_ft()
	{
		this.X_ft = this.startActProcessDTO.getW_t() - this.X_rt;
	}
}
