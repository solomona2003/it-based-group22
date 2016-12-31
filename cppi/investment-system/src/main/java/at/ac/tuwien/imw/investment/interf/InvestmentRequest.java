package at.ac.tuwien.imw.investment.interf;

import java.util.Date;

public class InvestmentRequest {
	
	private double X_r;
	
	private double X_f;
	
	private Date timestamp;
	

	
	public InvestmentRequest(double X_r, double X_f, Date timestamp) {
		
		this.X_r = X_r;	
		this.X_f = X_f;	
		this.timestamp = timestamp;
	}

	public double getX_r() {
		return X_r;
	}
	
	public double getX_f() {
		return X_f;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
}

