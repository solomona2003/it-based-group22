package at.ac.tuwien.imw.stock.price;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * StockPriceGenerator spg = new StockPriceGenerator(1); spg.schedule();
 *
 *
 */
@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class StockPriceApplication implements RabbitListenerConfigurer
{
	public static final String PDCA_EXCHANGE = "pdca";

	public static final String STOCK_PRICE_UPDATE_QUEUE = "stockPriceUpdateQueue";

	public static final String STOCK_PRICE_SETTINGS_QUEUE = "stockPriceSettingsQueue";

	public static void main( final String[] args )
	{
		SpringApplication.run( StockPriceApplication.class, args );
	}

	@Bean
	public TopicExchange pdcaExchange()
	{
		return new TopicExchange( PDCA_EXCHANGE );
	}

	@Bean
	public Queue stockPriceUpdateQueue()
	{
		return new Queue( STOCK_PRICE_UPDATE_QUEUE );
	}

	@Bean
	public Binding stockPriceUpdateQueueBinding()
	{
		return BindingBuilder.bind( stockPriceUpdateQueue() ).to( pdcaExchange() ).with( STOCK_PRICE_UPDATE_QUEUE );
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

	@Override
	public void configureRabbitListeners( final RabbitListenerEndpointRegistrar registrar )
	{
		registrar.setMessageHandlerMethodFactory( messageHandlerMethodFactory() );
	}
}
