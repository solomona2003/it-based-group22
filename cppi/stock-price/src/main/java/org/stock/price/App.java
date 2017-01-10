package org.stock.price;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	StockPriceGenerator spg = new StockPriceGenerator(1);
    	spg.schedule();
    }
}
