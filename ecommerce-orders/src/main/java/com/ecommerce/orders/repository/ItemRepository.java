package com.ecommerce.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.orders.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{

}
