package com.ecommerce.orders.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Service;

import com.ecommerce.orders.domain.Order;

@Service
public class OrderService {
	private List<Order> orderList = new ArrayList<>(Arrays.asList(new Order(1, "Groceries",250), new Order(2,"sports", 500)));
	
	public List<Order> getOrders(){
		return orderList;
	}
	
	public Order getOrder(int id) {
		return orderList.stream().filter(o -> o.getId()==id).findFirst().get();
	}
	
	public void addOrder(Order order) {
		orderList.add(order);
	}
	
	public void updateOrder(Order order, int id) {
		ListIterator<Order> ordersIterator = orderList.listIterator();
		while (ordersIterator.hasNext()){
			Order o = ordersIterator.next();
			if(o.getId()==id) {
				ordersIterator.set(order);
				return;
			}
		}
	}
	
	public void deleteOrder(int id) {
		orderList.removeIf(o -> o.getId()==id);
	}

}
