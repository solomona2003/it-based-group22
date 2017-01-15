package at.ac.tuwien.imw.investment.interf;

import java.time.Instant;

public class InvestmentResponse {
	public enum Flag {		
	
		SUCCESSFUL,
		UNSUCCESSFUL 			
	}
	
	private Flag value;
	private Instant timeStamp;
	
	public InvestmentResponse(Flag value, Instant timeStamp) {
		this.value = value;
		this.timeStamp = timeStamp;
	}
	
	public Flag getValue() {
		return value;
	}
	
	public Instant getTimeStamp() {
		return this.timeStamp;
	}
}


