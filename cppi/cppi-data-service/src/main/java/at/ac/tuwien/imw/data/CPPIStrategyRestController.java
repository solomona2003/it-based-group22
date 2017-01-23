package at.ac.tuwien.imw.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import at.ac.tuwien.imw.data.db.queries.PlanConfigurationRepository;
import at.ac.tuwien.imw.data.db.queries.SimulationPeriodValuesRepository;
import at.ac.tuwien.imw.data.dbmodel.PlanConfiguration;
import at.ac.tuwien.imw.data.dbmodel.SimulationPeriodValues;
import at.ac.tuwien.imw.data.dto.AppliedCheckDTO;
import at.ac.tuwien.imw.data.dto.AppliedPlanConfigurationDTO;
import at.ac.tuwien.imw.data.dto.AppliedTsrDTO;
import at.ac.tuwien.imw.data.dto.ExposuresDTO;
import at.ac.tuwien.imw.data.dto.StartActProcessDTO;
import at.ac.tuwien.imw.data.dto.StartCheckProcessDTO;

@Service
@RequestMapping( "/calculated" )
public class CPPIStrategyRestController
{
	private static final Logger LOG = LoggerFactory.getLogger( CPPIStrategyRestController.class );

	public static final String SET_CONFIGURATION_URL = CPPIDataServiceApplication.URL + "/calculated/setConfiguration";

	public static final String SET_TSR_URL = CPPIDataServiceApplication.URL + "/calculated/setTsr";

	public static final String SET_APPLIED_CHECK_URL = CPPIDataServiceApplication.URL + "/calculated/setAppliedCheck";

	public static final String SET_EXPOSURES_URL = CPPIDataServiceApplication.URL + "/calculated/setExposures";

	@Autowired
	private PlanConfigurationRepository planConfigurationRepository;

	@Autowired
	private SimulationPeriodValuesRepository simulationPeriodRepository;

	@Autowired
	private CPPIStrategyMessageSender cppiStrategyMessageSender;

	@RequestMapping( value = "/setConfiguration", method = RequestMethod.POST )
	@ResponseStatus( HttpStatus.OK )
	public void setAppliedPlanConfiguration( @RequestBody final AppliedPlanConfigurationDTO appliedPlanConfiguration )
	{
		LOG.info( "received plan configuration after planning" + appliedPlanConfiguration );
		final PlanConfiguration planConfiguration =
				this.planConfigurationRepository.findOne( appliedPlanConfiguration.getId() );
		planConfiguration.setR_t( appliedPlanConfiguration.getR_t() );
		planConfiguration.setT_0T( appliedPlanConfiguration.getT_0T() );
		planConfiguration.setD( appliedPlanConfiguration.getD() );
		this.planConfigurationRepository.save( planConfiguration );
	}

	@RequestMapping( value = "/setTsr", method = RequestMethod.POST )
	@ResponseStatus( HttpStatus.OK )
	public void setTsr( @RequestBody final AppliedTsrDTO appliedTsr )
	{
		LOG.info( "received TSR value after measure" + appliedTsr );
		final PlanConfiguration planConfiguration =
				this.planConfigurationRepository.findOne( appliedTsr.getConfigurationId() );
		final int lastIndex = planConfiguration.getSimulationPeriodValues().size() - 1;
		final SimulationPeriodValues periodValues = planConfiguration.getSimulationPeriodValues().get( lastIndex );
		periodValues.setTSR_t( appliedTsr.getTsr() );
		periodValues.setT_tT( appliedTsr.getT_tT() );
		this.simulationPeriodRepository.save( periodValues );
		final Double X_rtm1 =
				lastIndex > 0 ? planConfiguration.getSimulationPeriodValues().get( lastIndex - 1 ).getX_rt() : null;
		final Double X_ftm1 =
				lastIndex > 0 ? planConfiguration.getSimulationPeriodValues().get( lastIndex - 1 ).getX_ft() : null;
		this.cppiStrategyMessageSender.sendCheckMessage( new StartCheckProcessDTO( appliedTsr.getTsr(),
				planConfiguration.getR_t(), planConfiguration.getF_T(), appliedTsr.getT_tT(), X_rtm1, X_ftm1,
				planConfiguration.getD(), planConfiguration.getW_0(), planConfiguration.getId() ) );
	}

	@RequestMapping( value = "/setAppliedCheck", method = RequestMethod.POST )
	@ResponseStatus( HttpStatus.OK )
	public void setAppliedCheck( @RequestBody final AppliedCheckDTO appliedCheckDTO )
	{
		LOG.info( "received values after check" + appliedCheckDTO );
		final PlanConfiguration planConfiguration =
				this.planConfigurationRepository.findOne( appliedCheckDTO.getConfigurationId() );
		final int lastIndex = planConfiguration.getSimulationPeriodValues().size() - 1;
		final SimulationPeriodValues periodValues = planConfiguration.getSimulationPeriodValues().get( lastIndex );
		periodValues.setW_t( appliedCheckDTO.getW_t() );
		periodValues.setC_t( appliedCheckDTO.getC_t() );
		periodValues.setF_t( appliedCheckDTO.getF_t() );
		periodValues.setW_t( appliedCheckDTO.getW_t() );
		this.simulationPeriodRepository.save( periodValues );

		final StartActProcessDTO startActProcessDTO =
				new StartActProcessDTO( planConfiguration.getM(), appliedCheckDTO.getC_t(), planConfiguration.getB(),
						appliedCheckDTO.getW_t(), planConfiguration.getId() );
		this.cppiStrategyMessageSender.sendActMessage( startActProcessDTO );
	}

	@RequestMapping( value = "/setExposures", method = RequestMethod.POST )
	@ResponseStatus( HttpStatus.OK )
	public void setExposures( @RequestBody final ExposuresDTO exposuresDTO )
	{
		LOG.info( "received exposure values after act" + exposuresDTO );
		final PlanConfiguration planConfiguration =
				this.planConfigurationRepository.findOne( exposuresDTO.getConfigurationId() );
		final int lastIndex = planConfiguration.getSimulationPeriodValues().size() - 1;
		final SimulationPeriodValues periodValues = planConfiguration.getSimulationPeriodValues().get( lastIndex );
		periodValues.setX_ft( exposuresDTO.getX_ft() );
		periodValues.setX_rt( exposuresDTO.getX_rt() );
		this.simulationPeriodRepository.save( periodValues );
		LOG.info( "simulation periods after act: " + periodValues );
		this.cppiStrategyMessageSender.sendDoMessage( exposuresDTO );
	}
}
