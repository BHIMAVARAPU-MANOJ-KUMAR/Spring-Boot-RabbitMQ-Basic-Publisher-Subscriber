package com.rabbitmq.rabbitmqdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
	
	private String orderId;
	private String orderName;
	private Integer quantity;
	private Double price;
}
