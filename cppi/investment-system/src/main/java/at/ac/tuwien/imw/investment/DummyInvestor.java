package at.ac.tuwien.imw.investment;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import at.ac.tuwien.imw.investment.interf.InvestmentResponse;
import at.ac.tuwien.imw.investment.interf.InvestmentResponse.Flag;

public class DummyInvestor
{

	public static InvestmentResponse invest( final double X_r, final double X_f )
	{
		final Instant timeOfInvestment = Instant.now();
		final String formattedTime = ZonedDateTime.ofInstant( timeOfInvestment, ZoneId.systemDefault() ).toString();
		System.out.println( "At Investment time: " + formattedTime + " Risky Investment is: " + X_r
				+ " Riskless Investment is: " + X_f );
		return new InvestmentResponse( Flag.SUCCESSFUL, timeOfInvestment );
	}
}
