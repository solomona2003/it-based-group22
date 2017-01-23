package at.ac.tuwien.imw.data.dto;

import java.util.List;

import at.ac.tuwien.imw.data.dbmodel.SimulationPeriodValues;

public class SimulatedPeriodsDTO
{
	private long configurationId;

	private List<SimulationPeriodValues> simulationPeriodValues;

	public SimulatedPeriodsDTO()
	{
	}

	public SimulatedPeriodsDTO( final long configurationId, final List<SimulationPeriodValues> simulationPeriodValues )
	{
		this.configurationId = configurationId;
		this.simulationPeriodValues = simulationPeriodValues;
	}

	public long getConfigurationId()
	{
		return this.configurationId;
	}

	public List<SimulationPeriodValues> getSimulationPeriodValues()
	{
		return this.simulationPeriodValues;
	}

	@Override
	public String toString()
	{
		return "SimulatedPeriodsDTO [configurationId=" + this.configurationId + ", simulationPeriodValues="
				+ this.simulationPeriodValues + "]";
	}
}
