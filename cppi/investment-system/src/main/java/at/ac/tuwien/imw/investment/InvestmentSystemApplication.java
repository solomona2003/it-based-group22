package at.ac.tuwien.imw.investment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvestmentSystemApplication
{
	public static String URL = "http://192.168.99.100:8083";

	public static void main( final String[] args )
	{
		SpringApplication.run( InvestmentSystemApplication.class, args );
	}
}
