package at.ac.tuwien.imw.data.dto;

import java.util.List;

import at.ac.tuwien.imw.data.dbmodel.PlanConfiguration;

public class HistoryDTO
{
	private final List<PlanConfiguration> history;

	private final Long latestConfigurationId;

	public HistoryDTO( final List<PlanConfiguration> history, final Long latestConfigurationId )
	{
		this.history = history;
		this.latestConfigurationId = latestConfigurationId;
	}

	public List<PlanConfiguration> getHistory()
	{
		return this.history;
	}

	public Long getLatestConfigurationId()
	{
		return this.latestConfigurationId;
	}

	@Override
	public String toString()
	{
		return "HistoryDTO [history=" + this.history + ", latestConfigurationId=" + this.latestConfigurationId + "]";
	}
}
