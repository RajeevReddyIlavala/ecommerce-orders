package com.ecommerce.orders.controller;


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
	
	/*
	 * @RequestMapping("/orders") public List<Order> getallOrders() { return
	 * orderService.getAllOrders(); }
	 */
	
	@RequestMapping("/orders/{id}")
	public Order getOrdeById(@PathVariable String id) {
		return orderService.getOrder(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/orders")
	public void createOrder(@RequestBody Order order) {
		orderService.createOrder(order);	
	}
	
	/*
	 * @RequestMapping(method = RequestMethod.PUT, value = "/orders/{id}") public
	 * void updateOrder(@RequestBody Order order, @PathVariable String id) {
	 * orderService.updateOrder(order, id); }
	 */
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/orders/{id}")
	public void cancelOrder(@PathVariable String id) {
		orderService.cancelOrder(id);
	}
	
}
