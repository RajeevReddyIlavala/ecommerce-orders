package com.ecommerce.orders.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.orders.repository.CustomerRepository;
import com.ecommerce.orders.domain.Customer;

@Service
public class CustomerService {
	@Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.get();
    }

    public Customer getOrCreateCustomer(Customer customer) {
        if(customer.getId() == null) {
            return customerRepository.save(customer);
        }
        return customerRepository.findById(customer.getId().toString()).get();
    }


}
