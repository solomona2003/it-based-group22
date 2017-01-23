package at.ac.tuwien.imw.investment;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.imw.investment.interf.InvestmentResponse;

@RestController
@RequestMapping( "" )
public class AppController
{

	@RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public InvestmentResponse investing( @RequestParam final double Xf, @RequestParam final double Xr )
	{
		return DummyInvestor.invest( Xr, Xf );
	}
}
