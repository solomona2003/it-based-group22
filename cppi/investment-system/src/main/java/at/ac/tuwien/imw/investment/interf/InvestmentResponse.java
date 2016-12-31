package at.ac.tuwien.imw.investment.interf;

public class InvestmentResponse {
	
	public enum Flag {		
		
		SUCCESSFUL,
		UNSUCCESSFUL 			
	}
	
	private Flag value;
	
	public InvestmentResponse(Flag value) {
		this.value = value;
	}
	
	public Flag getValue() {
		return value;
	}
}


