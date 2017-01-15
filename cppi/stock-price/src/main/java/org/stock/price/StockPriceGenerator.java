package org.stock.price;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * 
 * the generator has an array of prices, 25 of them, 
 * then it takes the user period specification as input 
 * and with the {@link ScheduledExecutorService} 
 * it makes stock objects by passing in the price from the array
 * 
 * at the stock, the stock makes the stock object with the price passed from the 
 * StockPriceGenerator and stamps the time at creation
 *
 */
public class StockPriceGenerator {
	
	private int userUpdatePeriod; 
	
	private int t;
	
	private static int[] PRICES = {100, 102, 105, 110, 115, 115, 115, 117, 
			120, 119, 116, 116, 116, 114, 118, 120, 125, 130, 123, 119, 116, 
			115, 114, 113, 120};
	
	public StockPriceGenerator(int period) {
		this.userUpdatePeriod = period;
		this.t = 0;
	}
	
	
	public void schedule() {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable task = new Runnable() {    //we are now in like an anonymous class , it is a class that implements the interface runnable
			@Override
			public void run() {	
				int latestPrice = PRICES[t];
				Stock stock = new Stock(latestPrice);
				System.out.println(stock.toString()); // just for the moment
				t++;
				if(t == PRICES.length) {
					scheduler.shutdown();
				}
			} 
		};
		scheduler.scheduleAtFixedRate(task, 0, userUpdatePeriod, TimeUnit.SECONDS);
	}
}
