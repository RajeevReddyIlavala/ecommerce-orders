package com.ecommerce.orders.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.orders.domain.Customer;
import com.ecommerce.orders.domain.Order;
import com.ecommerce.orders.domain.OrderStatus;
import com.ecommerce.orders.exception.IllegalOrderException;
import com.ecommerce.orders.exception.OrderNotFoundException;
import com.ecommerce.orders.repository.OrderRepository;

@Service
public class OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    public Order getOrder(String orderID) {
        try {
            UUID uuid = UUID.fromString(orderID);
            Optional<Order> ordersOptional = orderRepository.findById(uuid);
            if(ordersOptional.isPresent()) {
                return ordersOptional.get();
            }
        } catch(IllegalArgumentException e) {
            throw new IllegalOrderException();
        }
        throw new OrderNotFoundException();
    }

    public Order createOrder(Order order) {
        Customer customer = customerService.getOrCreateCustomer(order.getCustomer());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PLACED);
        return orderRepository.save(order);
    }

    public Order cancelOrder(String orderID) {
        Order order = getOrder(orderID);
        order.setStatus(OrderStatus.CANCELLED);
        return order;
    }

    public Order updateOrderByStatus(String orderID, OrderStatus status){
        Order order = getOrder(orderID);
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
