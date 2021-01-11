package com.ecommerce.orders.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersProcessingController {
	
	@RequestMapping("/orders")
	public String getOrders() {
		return "Your Orders are here";
	}
	
}
