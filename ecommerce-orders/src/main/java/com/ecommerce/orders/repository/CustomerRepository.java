package com.ecommerce.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.orders.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

}
