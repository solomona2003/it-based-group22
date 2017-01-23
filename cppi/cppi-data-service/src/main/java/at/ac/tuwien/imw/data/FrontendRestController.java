package at.ac.tuwien.imw.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import at.ac.tuwien.imw.data.db.queries.PlanConfigurationRepository;
import at.ac.tuwien.imw.data.dbmodel.PlanConfiguration;
import at.ac.tuwien.imw.data.dto.HistoryDTO;
import at.ac.tuwien.imw.data.dto.SimulatedPeriodsDTO;
import at.ac.tuwien.imw.data.dto.UserConfigurationDTO;

@Service
@RequestMapping( "frontend" )
public class FrontendRestController
{

	private static final Logger LOG = LoggerFactory.getLogger( FrontendRestController.class );

	@Autowired
	private CPPIStrategyMessageSender cppiStrategyMessageSender;

	@Autowired
	private PlanConfigurationRepository planConfigurationRepository;

	@CrossOrigin
	@RequestMapping( value = "/configuration", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody HistoryDTO setPlanConfiguration( @RequestBody final UserConfigurationDTO userConfiguration )
	{
		LOG.info( "Requested message " + userConfiguration );

		final PlanConfiguration planConfiguration = new PlanConfiguration();
		planConfiguration.setB( userConfiguration.getB() );
		planConfiguration.setF_T( userConfiguration.getF_T() );
		planConfiguration.setM( userConfiguration.getM() );
		planConfiguration.setT( userConfiguration.getT() );
		planConfiguration.setW_0( userConfiguration.getW_0() );

		this.planConfigurationRepository.save( planConfiguration );
		userConfiguration.setId( planConfiguration.getId() );
		this.cppiStrategyMessageSender.sendPlanMessage( userConfiguration );

		return createHistory( planConfiguration.getId() );
	}

	@CrossOrigin
	@RequestMapping( value = "/history", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody HistoryDTO getHistory()
	{
		LOG.info( "Requested results history" );

		return createHistory( null );
	}

	@CrossOrigin
	@RequestMapping( value = "/latestsimulation", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody SimulatedPeriodsDTO
			getSimulatedPeriods( @RequestParam( value = "configurationId" ) final long configurationId )
	{
		LOG.info( "Requested latest update for configuration with id " + configurationId );

		final PlanConfiguration planConfiguration = this.planConfigurationRepository.findOne( configurationId );
		if ( planConfiguration != null )
		{
			LOG.info( planConfiguration.toString() );
			return new SimulatedPeriodsDTO( planConfiguration.getId(), planConfiguration.getSimulationPeriodValues() );
		}
		else
		{
			return null;
		}
	}

	private HistoryDTO createHistory( final Long latestConfigurationId )
	{
		final List<PlanConfiguration> simulations = new ArrayList<>();
		for ( final PlanConfiguration simulation : this.planConfigurationRepository.findAll() )
		{
			simulations.add( simulation );
		}
		return new HistoryDTO( simulations, latestConfigurationId );
	}
}
