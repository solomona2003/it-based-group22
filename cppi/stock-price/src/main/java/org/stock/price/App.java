package org.stock.price;

public class App 
{
    public static void main( String[] args )
    {
    	StockPriceGenerator spg = new StockPriceGenerator(1);
    	spg.schedule();
    }
}
