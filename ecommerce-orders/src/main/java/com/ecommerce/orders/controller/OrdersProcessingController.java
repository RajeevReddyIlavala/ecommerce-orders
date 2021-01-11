package com.ecommerce.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.orders.domain.Order;
import com.ecommerce.orders.service.OrderService;

@RestController
public class OrdersProcessingController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/orders")
	public List<Order> getallOrders() {
		return orderService.getOrders();
	}
	
	@RequestMapping("/orders/{id}")
	public Order getOrder(@PathVariable int id) {
		return orderService.getOrder(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/orders")
	public void addOrder(@RequestBody Order order) {
		orderService.addOrder(order);	
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/orders/{id}")
	public void updateOrder(@RequestBody Order order, @PathVariable int id) {
		orderService.updateOrder(order, id);		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/orders/{id}")
	public void deleteOrder(@PathVariable int id) {
		orderService.deleteOrder(id);
	}
	
}
