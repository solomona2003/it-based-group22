package at.ac.tuwien.imw.cppi.strategy;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import at.ac.tuwien.imw.data.CPPIStrategyMessageSender;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class CPPIStrategyApplication implements RabbitListenerConfigurer
{
	public static final String EXCHANGE_NAME = "pdca";

	public static void main(final String[] args)
	{
		SpringApplication.run( CPPIStrategyApplication.class, args );
	}

	@Bean
	public TopicExchange pdcaExchange()
	{
		return new TopicExchange( EXCHANGE_NAME );
	}

	@Bean
	public Queue planQueue()
	{
		return new Queue( CPPIStrategyMessageSender.PLAN_REQUEST_QUEUE );
	}

	@Bean
	public Binding planQueueBinding()
	{
		return BindingBuilder.bind( planQueue() ).to( pdcaExchange() )
				.with( CPPIStrategyMessageSender.PLAN_REQUEST_QUEUE );
	}

	@Bean
	public Queue measureQueue()
	{
		return new Queue( CPPIStrategyMessageSender.MEASURE_REQUEST_QUEUE );
	}

	@Bean
	public Binding measureQueueBinding()
	{
		return BindingBuilder.bind( measureQueue() ).to( pdcaExchange() )
				.with( CPPIStrategyMessageSender.MEASURE_REQUEST_QUEUE );
	}

	@Bean
	public Queue doQueue()
	{
		return new Queue( CPPIStrategyMessageSender.DO_REQUEST_QUEUE );
	}

	@Bean
	public Binding doQueueBinding()
	{
		return BindingBuilder.bind( doQueue() ).to( pdcaExchange() ).with( CPPIStrategyMessageSender.DO_REQUEST_QUEUE );
	}

	@Bean
	public Queue actQueue()
	{
		return new Queue( CPPIStrategyMessageSender.ACT_REQUEST_QUEUE );
	}

	@Bean
	public Binding actQueueBinding()
	{
		return BindingBuilder.bind( actQueue() ).to( pdcaExchange() )
				.with( CPPIStrategyMessageSender.ACT_REQUEST_QUEUE );
	}

	@Bean
	public Queue checkQueue()
	{
		return new Queue( CPPIStrategyMessageSender.CHECK_REQUEST_QUEUE );
	}

	@Bean
	public Binding checkQueueBinding()
	{
		return BindingBuilder.bind( checkQueue() ).to( pdcaExchange() )
				.with( CPPIStrategyMessageSender.CHECK_REQUEST_QUEUE );
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
	{
		final RabbitTemplate rabbitTemplate = new RabbitTemplate( connectionFactory );
		final Jackson2JsonMessageConverter producerJackson2MessageConverter = new Jackson2JsonMessageConverter();
		rabbitTemplate.setMessageConverter( producerJackson2MessageConverter );
		return rabbitTemplate;
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory()
	{
		final DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		final MappingJackson2MessageConverter consumerJackson2MessageConverter = new MappingJackson2MessageConverter();
		factory.setMessageConverter( consumerJackson2MessageConverter );
		return factory;
	}

	@Bean
	public RabbitAdmin rabbitAdmin(final ConnectionFactory connectionFactory)
	{
		return new RabbitAdmin( connectionFactory );
	}

	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar)
	{
		registrar.setMessageHandlerMethodFactory( messageHandlerMethodFactory() );
	}

}
