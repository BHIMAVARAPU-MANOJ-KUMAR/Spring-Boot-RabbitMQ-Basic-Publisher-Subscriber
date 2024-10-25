package com.rabbitmq.rabbitmqdemo.mqconfiguration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

	public static final String QUEUE = "MQ_DEMO";
	public static final String EXCHANGE = "MQ_DEMO_EXCHANGE";
	public static final String ROUTING_KEY = "rabbitmqdemoroutingkey";

	@Bean
	Queue queue() {
		return new Queue(QUEUE);
	}

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
	}

	@Bean
	MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}