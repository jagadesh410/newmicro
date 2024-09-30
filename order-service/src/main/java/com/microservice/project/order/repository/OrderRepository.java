package com.microservice.project.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.project.order.model.OrderProduct;

@Repository
public interface OrderRepository extends JpaRepository<OrderProduct, Long>{

}
