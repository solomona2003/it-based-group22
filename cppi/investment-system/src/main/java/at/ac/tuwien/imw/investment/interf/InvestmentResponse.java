package at.ac.tuwien.imw.investment.interf;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InvestmentResponse
{
	public enum Flag
	{
		SUCCESSFUL,
		UNSUCCESSFUL
	}

	private Flag value;

	private String timeStamp;

	public InvestmentResponse()
	{
		// for JSON deserialization
	}

	public InvestmentResponse( final Flag value, final Instant currentTime )
	{
		this.value = value;
		this.timeStamp = ZonedDateTime.ofInstant( currentTime, ZoneId.systemDefault() ).toString();
	}

	public Flag getValue()
	{
		return this.value;
	}

	public String getTimeStamp()
	{
		return this.timeStamp;
	}

	@Override
	public String toString()
	{
		return "InvestmentResponse [value=" + this.value + ", timeStamp=" + this.timeStamp + "]";
	}
}
