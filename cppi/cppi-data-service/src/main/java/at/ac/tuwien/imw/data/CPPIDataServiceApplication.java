package at.ac.tuwien.imw.data;

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
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class CPPIDataServiceApplication implements RabbitListenerConfigurer
{
	public static final String PDCA_EXCHANGE = "pdca";

	public static final String URL = "http://localhost:8082";

	public static void main( final String[] args )
	{
		SpringApplication.run( CPPIDataServiceApplication.class, args );
	}

	@Bean
	public TopicExchange pdcaExchange()
	{
		return new TopicExchange( PDCA_EXCHANGE );
	}

	@Bean
	public Queue planRequestQueue()
	{
		return new Queue( CPPIStrategyMessageSender.PLAN_REQUEST_QUEUE );
	}

	@Bean
	public Binding planRequestQueueBinding()
	{
		return BindingBuilder.bind( planRequestQueue() ).to( pdcaExchange() )
				.with( CPPIStrategyMessageSender.PLAN_REQUEST_QUEUE );
	}

	@Bean
	public Queue measureRequestQueue()
	{
		return new Queue( CPPIStrategyMessageSender.MEASURE_REQUEST_QUEUE );
	}

	@Bean
	public Binding measureRequestQueueBinding()
	{
		return BindingBuilder.bind( measureRequestQueue() ).to( pdcaExchange() )
				.with( CPPIStrategyMessageSender.MEASURE_REQUEST_QUEUE );
	}

	@Bean
	public Queue checkRequestQueue()
	{
		return new Queue( CPPIStrategyMessageSender.CHECK_REQUEST_QUEUE );
	}

	@Bean
	public Binding checkRequestQueueBinding()
	{
		return BindingBuilder.bind( checkRequestQueue() ).to( pdcaExchange() )
				.with( CPPIStrategyMessageSender.CHECK_REQUEST_QUEUE );
	}

	@Bean
	public Queue actRequestQueue()
	{
		return new Queue( CPPIStrategyMessageSender.ACT_REQUEST_QUEUE );
	}

	@Bean
	public Binding actRequestQueueBinding()
	{
		return BindingBuilder.bind( actRequestQueue() ).to( pdcaExchange() )
				.with( CPPIStrategyMessageSender.ACT_REQUEST_QUEUE );
	}

	@Bean
	public Queue doRequestQueue()
	{
		return new Queue( CPPIStrategyMessageSender.DO_REQUEST_QUEUE );
	}

	@Bean
	public Binding doRequestQueueBinding()
	{
		return BindingBuilder.bind( doRequestQueue() ).to( pdcaExchange() )
				.with( CPPIStrategyMessageSender.DO_REQUEST_QUEUE );
	}

	@Bean
	public Queue stockPriceUpdateQueue()
	{
		return new Queue( StockPriceSystemMessageListener.STOCK_PRICE_UPDATE_QUEUE );
	}

	@Bean
	public Binding stockPriceUpdateQueueBinding()
	{
		return BindingBuilder.bind( stockPriceUpdateQueue() ).to( pdcaExchange() )
				.with( StockPriceSystemMessageListener.STOCK_PRICE_UPDATE_QUEUE );
	}

	@Bean
	public RabbitTemplate rabbitTemplate( final ConnectionFactory connectionFactory )
	{
		final RabbitTemplate rabbitTemplate = new RabbitTemplate( connectionFactory );
		final Jackson2JsonMessageConverter producerConverter = new Jackson2JsonMessageConverter();
		rabbitTemplate.setMessageConverter( producerConverter );
		return rabbitTemplate;
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory()
	{
		final DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		final MappingJackson2MessageConverter consumerConverter = new MappingJackson2MessageConverter();
		factory.setMessageConverter( consumerConverter );
		return factory;
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public RabbitAdmin rabbitAdmin( final ConnectionFactory connectionFactory )
	{
		return new RabbitAdmin( connectionFactory );
	}

	@Override
	public void configureRabbitListeners( final RabbitListenerEndpointRegistrar registrar )
	{
		registrar.setMessageHandlerMethodFactory( messageHandlerMethodFactory() );
	}
}
