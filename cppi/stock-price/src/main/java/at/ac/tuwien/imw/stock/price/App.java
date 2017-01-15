package at.ac.tuwien.imw.stock.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * StockPriceGenerator spg = new StockPriceGenerator(1);
   spg.schedule();
 *
 *
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
