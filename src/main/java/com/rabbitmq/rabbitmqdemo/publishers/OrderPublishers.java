package com.rabbitmq.rabbitmqdemo.publishers;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.rabbitmqdemo.entity.Order;
import com.rabbitmq.rabbitmqdemo.entity.OrderStatus;
import com.rabbitmq.rabbitmqdemo.mqconfiguration.Configurations;

@RestController
@RequestMapping("/order")
@Component
public class OrderPublishers {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping("/{restaurantName}")
	public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderStatus orderStatus = new OrderStatus(order, "PROCESSED", "Order Placed Succesfully in " + restaurantName);
		rabbitTemplate.convertAndSend(Configurations.EXCHANGE, Configurations.ROUTING_KEY, orderStatus);
		return "Success !!";
	}
	
	@Scheduled(initialDelay = 1000, fixedDelay = 2000)
	public void sendMessage() {
		String message = "Message from Scheduling . . .";
		rabbitTemplate.convertAndSend(Configurations.EXCHANGE, Configurations.ROUTING_KEY, message);
	}
}