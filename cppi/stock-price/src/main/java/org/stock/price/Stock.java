package org.stock.price;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Stock {
	private int price;
	private Instant timeStamp;
	
	public Stock(int p) {
		this.price = p;
		this.timeStamp = Instant.now();
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public Instant getTimeStamp() {
		return this.timeStamp;
	}
	
	@Override 
    public String toString() {
		String formattedTime = ZonedDateTime.ofInstant(timeStamp, ZoneId.systemDefault()).toString();
	    return "price:" + price + " and time stamp:" + formattedTime;
	}
} 

