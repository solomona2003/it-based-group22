package at.ac.tuwien.imw.stock.price;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * at the stock, the stock makes the stock object with the price passed from the
 * StockPriceGenerator and makes stamps the time at creation
 *
 */

public class Stock
{
	private int price;

	private String timeStamp;

	public Stock()
	{
		// needed for JSON deserialization
	}

	public Stock( final int p )
	{
		this.price = p;
		final Instant currentTime = Instant.now();
		this.timeStamp = ZonedDateTime.ofInstant( currentTime, ZoneId.systemDefault() ).toString();
	}

	public int getPrice()
	{
		return this.price;
	}

	public String getTimeStamp()
	{
		return this.timeStamp;
	}

	@Override
	public String toString()
	{
		return "Stock [price=" + this.price + ", timeStamp=" + this.timeStamp + "]";
	}
}
