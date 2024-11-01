package com.rabbitmq.rabbitmqdemo.subscribers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.rabbitmqdemo.entity.OrderStatus;
import com.rabbitmq.rabbitmqdemo.mqconfiguration.Configurations;

@Component
public class Subscribers {
	
	@RabbitListener(queues = Configurations.QUEUE)
	public void consumeMessageFromQueue(OrderStatus status) {
		System.out.println("Message received from Queue : " + status);
	}
	
	@RabbitListener(queues = Configurations.QUEUE)
	public void consumeMessageStringFromQueue(String message) {
		System.out.println("Message received from Queue. "
				+ "Implemented by Scheduling Concept. " + 
				message);
	}
}