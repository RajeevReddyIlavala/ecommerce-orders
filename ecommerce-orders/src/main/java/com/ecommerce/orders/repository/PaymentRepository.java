package com.ecommerce.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.orders.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
}
