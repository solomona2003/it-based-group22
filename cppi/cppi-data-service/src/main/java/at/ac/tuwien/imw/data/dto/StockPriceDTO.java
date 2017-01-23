package at.ac.tuwien.imw.data.dto;

/**
 * Contains the current stock price (period t) and the stock price from t-1.
 * Used for calculating TSR.
 */
public class StockPriceDTO
{
	private int stockPriceCurrent;

	private Integer stockPricePrevious;

	private int currentPeriod;

	private long configurationId;

	private double t_0T;

	private double d;

	public StockPriceDTO()
	{
	}

	public StockPriceDTO( final int stockPriceCurrent, final Integer stockPricePrevious, final int currentPeriod,
			final long configurationId, final double t_0t, final double d )
	{
		this.stockPriceCurrent = stockPriceCurrent;
		this.stockPricePrevious = stockPricePrevious;
		this.currentPeriod = currentPeriod;
		this.configurationId = configurationId;
		this.t_0T = t_0t;
		this.d = d;
	}

	public int getStockPriceCurrent()
	{
		return this.stockPriceCurrent;
	}

	public Integer getStockPricePrevious()
	{
		return this.stockPricePrevious;
	}

	public int getCurrentPeriod()
	{
		return this.currentPeriod;
	}

	public long getConfigurationId()
	{
		return this.configurationId;
	}

	public double getT_0T()
	{
		return this.t_0T;
	}

	public double getD()
	{
		return this.d;
	}

	@Override
	public String toString()
	{
		return "StockPriceDTO [stockPriceCurrent=" + this.stockPriceCurrent + ", stockPricePrevious="
				+ this.stockPricePrevious + ", currentPeriod=" + this.currentPeriod + ", configurationId="
				+ this.configurationId + ", t_0T=" + this.t_0T + ", d=" + this.d + "]";
	}
}
