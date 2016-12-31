package at.ac.tuwien.imw.data.interf;

import java.util.Date;

public class ReadFromStock {
	private int S_t;

	private Date timestamp;
	
	public ReadFromStock(int S_t, Date timestamp) {
		this.S_t = S_t;
		this.timestamp = timestamp;
	}
	
	public int getS_t(){
		return S_t;
	}
		
	public Date getTimeStamp(){
		return timestamp;
	}
}

