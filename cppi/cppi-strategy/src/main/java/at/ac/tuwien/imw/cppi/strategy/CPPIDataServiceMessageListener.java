package at.ac.tuwien.imw.cppi.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.imw.data.CPPIStrategyMessageSender;
import at.ac.tuwien.imw.data.dto.ExposuresDTO;
import at.ac.tuwien.imw.data.dto.StartActProcessDTO;
import at.ac.tuwien.imw.data.dto.StartCheckProcessDTO;
import at.ac.tuwien.imw.data.dto.StockPriceDTO;
import at.ac.tuwien.imw.data.dto.UserConfigurationDTO;
import at.ac.tuwien.imw.pdca.fw.acting.impl.ActProcessImpl;
import at.ac.tuwien.imw.pdca.fw.acting.impl.CorrectiveActRulesImpl;
import at.ac.tuwien.imw.pdca.fw.checking.impl.CheckProcessImpl;
import at.ac.tuwien.imw.pdca.fw.checking.impl.CheckingRulesImpl;
import at.ac.tuwien.imw.pdca.fw.doing.DoProcessImpl;
import at.ac.tuwien.imw.pdca.fw.doing.DoRulesImpl;
import at.ac.tuwien.imw.pdca.fw.measuring.impl.MeasureProcess;
import at.ac.tuwien.imw.pdca.fw.measuring.impl.MeasureRulesImpl;
import at.ac.tuwien.iw.pdca.fw.planning.impl.PlanConfigurationImpl;
import at.ac.tuwien.iw.pdca.fw.planning.impl.PlanProcessImpl;
import at.ac.tuwien.iw.pdca.fw.planning.impl.PlanningRulesImpl;

@Service
public class CPPIDataServiceMessageListener
{
	private static final Logger LOG = LoggerFactory.getLogger( CPPIDataServiceMessageListener.class );

	@Autowired
	public CPPIDataServiceMessageListener( final RabbitAdmin admin )
	{
		admin.purgeQueue( CPPIStrategyMessageSender.PLAN_REQUEST_QUEUE, false );
		admin.purgeQueue( CPPIStrategyMessageSender.MEASURE_REQUEST_QUEUE, false );
		admin.purgeQueue( CPPIStrategyMessageSender.CHECK_REQUEST_QUEUE, false );
		admin.purgeQueue( CPPIStrategyMessageSender.ACT_REQUEST_QUEUE, false );
		admin.purgeQueue( CPPIStrategyMessageSender.DO_REQUEST_QUEUE, false );
	}

	@RabbitListener( queues = CPPIStrategyMessageSender.PLAN_REQUEST_QUEUE )
	public void onPlanMessage(final UserConfigurationDTO userConfiguration)
	{
		LOG.info( "User configuration received: " + userConfiguration );
		final PlanConfigurationImpl planConfiguration = new PlanConfigurationImpl( userConfiguration );
		final PlanningRulesImpl planningRules = new PlanningRulesImpl( planConfiguration );
		final PlanProcessImpl planProcess = new PlanProcessImpl( planningRules );
		planProcess.plan();
	}

	@RabbitListener( queues = CPPIStrategyMessageSender.MEASURE_REQUEST_QUEUE )
	public void onMeasureMessage(final StockPriceDTO stockPrice)
	{
		LOG.info( "Stock price received: " + stockPrice );
		final MeasureRulesImpl measureRules = new MeasureRulesImpl( stockPrice );
		final MeasureProcess measureProcess = new MeasureProcess( measureRules );
		measureProcess.updateStockPrice();
	}

	@RabbitListener( queues = CPPIStrategyMessageSender.DO_REQUEST_QUEUE )
	public void onDoMessage(final ExposuresDTO exposures)
	{
		LOG.info( "To be invested exposures received: " + exposures );
		final DoRulesImpl doRules = new DoRulesImpl( exposures );
		final DoProcessImpl doProcess = new DoProcessImpl( doRules );
		doProcess.operate();
	}

	@RabbitListener( queues = CPPIStrategyMessageSender.CHECK_REQUEST_QUEUE )
	public void onCheckMessage(final StartCheckProcessDTO startCheckProcessDTO)
	{
		LOG.info( "Trigger for check process received: " + startCheckProcessDTO );
		final CheckingRulesImpl checkingRules = new CheckingRulesImpl( startCheckProcessDTO );
		final CheckProcessImpl checkProcess = new CheckProcessImpl( checkingRules );
		checkProcess.getCheckResult( null, null );
	}

	@RabbitListener( queues = CPPIStrategyMessageSender.ACT_REQUEST_QUEUE )
	public void onActMessage(final StartActProcessDTO startActProcessDTO)
	{
		LOG.info( "Trigger for act process received: " + startActProcessDTO );
		final CorrectiveActRulesImpl actRulesImpl = new CorrectiveActRulesImpl( startActProcessDTO );
		final ActProcessImpl actProcess = new ActProcessImpl( actRulesImpl );
		actProcess.act( null );
	}
}
