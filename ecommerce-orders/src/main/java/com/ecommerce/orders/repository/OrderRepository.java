package com.ecommerce.orders.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.orders.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,UUID>	{
	

}
	