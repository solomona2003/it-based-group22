package at.ac.tuwien.imw.pdca.fw.doing;

import java.net.URI;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import at.ac.tuwien.imw.data.dto.ExposuresDTO;
import at.ac.tuwien.imw.investment.interf.InvestmentResponse;
import at.ac.tuwien.imw.pdca.fw.DoRules;

public class DoRulesImpl implements DoRules
{
	private final ExposuresDTO exposures;

	public DoRulesImpl( final ExposuresDTO exposures )
	{
		this.exposures = exposures;
	}

	@Override
	public void applyDoRules()
	{
		invest( this.exposures.getX_ft(), this.exposures.getX_rt() );
	}

	private InvestmentResponse invest(final double X_f, final double X_r)
	{
		final URI uri = UriComponentsBuilder.fromHttpUrl( at.ac.tuwien.imw.investment.InvestmentSystemApplication.URL )
				.queryParam( "Xf", String.valueOf( X_f ) ).queryParam( "Xr", String.valueOf( X_r ) ).build().toUri();
		final RestTemplate restTemplate = new RestTemplate();
		final InvestmentResponse response = restTemplate.getForObject( uri, InvestmentResponse.class );

		return response;
	}
}
