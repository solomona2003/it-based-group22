package at.ac.tuwien.imw.data;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.imw.data.dto.ExposuresDTO;
import at.ac.tuwien.imw.data.dto.StartActProcessDTO;
import at.ac.tuwien.imw.data.dto.StartCheckProcessDTO;
import at.ac.tuwien.imw.data.dto.StockPriceDTO;
import at.ac.tuwien.imw.data.dto.UserConfigurationDTO;

@Service
public class CPPIStrategyMessageSender
{
	public static final String PLAN_REQUEST_QUEUE = "planRequest";

	public static final String MEASURE_REQUEST_QUEUE = "measureRequest";

	public static final String CHECK_REQUEST_QUEUE = "checkRequest";

	public static final String ACT_REQUEST_QUEUE = "actRequest";

	public static final String DO_REQUEST_QUEUE = "doRequest";

	// used to convert and send message
	private final RabbitTemplate rabbitTemplate;

	@Autowired
	public CPPIStrategyMessageSender( final RabbitTemplate rabbitTemplate, final RabbitAdmin admin )
	{
		this.rabbitTemplate = rabbitTemplate;
		admin.purgeQueue( PLAN_REQUEST_QUEUE, false );
		admin.purgeQueue( MEASURE_REQUEST_QUEUE, false );
		admin.purgeQueue( CHECK_REQUEST_QUEUE, false );
		admin.purgeQueue( ACT_REQUEST_QUEUE, false );
		admin.purgeQueue( DO_REQUEST_QUEUE, false );
	}

	public void sendPlanMessage( final UserConfigurationDTO userConfigurationDTO )
	{
		this.rabbitTemplate.convertAndSend( PLAN_REQUEST_QUEUE, userConfigurationDTO );
	}

	public void sendMeasureMessage( final StockPriceDTO stockPriceDTO )
	{
		this.rabbitTemplate.convertAndSend( MEASURE_REQUEST_QUEUE, stockPriceDTO );
	}

	public void sendCheckMessage( final StartCheckProcessDTO startCheckProcessDTO )
	{
		this.rabbitTemplate.convertAndSend( CHECK_REQUEST_QUEUE, startCheckProcessDTO );
	}

	public void sendActMessage( final StartActProcessDTO startActProcessDTO )
	{
		this.rabbitTemplate.convertAndSend( ACT_REQUEST_QUEUE, startActProcessDTO );
	}

	public void sendDoMessage( final ExposuresDTO exposuresDTO )
	{
		this.rabbitTemplate.convertAndSend( DO_REQUEST_QUEUE, exposuresDTO );
	}
}
