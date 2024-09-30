package com.microservice.project.order.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.microservice.project.order.dto.OrderRequest;
import com.microservice.project.order.dto.OrderResponse;


public interface OrderService {
	
	List<OrderResponse> getOrders();
	boolean placeOrder(Long id, int quantity);
	boolean deleteOrder(Long id);
	boolean deleteAllOrder();

}
