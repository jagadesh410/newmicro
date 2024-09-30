package com.microservice.project.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.project.order.client.InventoryClient;
import com.microservice.project.order.dto.OrderRequest;
import com.microservice.project.order.dto.OrderResponse;
import com.microservice.project.order.serviceimpl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@GetMapping("/getOrders")
	public ResponseEntity<?> getOrders() {
		List<OrderResponse> responseList = orderServiceImpl.getOrders();
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@PostMapping("/placeOrder/{id}/{quantity}")
	public ResponseEntity<?> placeOrder(@PathVariable Long id, @PathVariable int quantity) {
		boolean isOrderPlaced = orderServiceImpl.placeOrder(id, quantity);
		if(isOrderPlaced)
			return new ResponseEntity<>("Order Placed Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("Unable to Place the order", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteOrderById/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
		boolean isOrderDeleted = orderServiceImpl.deleteOrder(id);
		if(isOrderDeleted)
			return new ResponseEntity<>("Order Deleted Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("Unable to Delete the order", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteAllOrders")
	public ResponseEntity<?> deleteAllOrders() {
		orderServiceImpl.deleteAllOrder();
		return new ResponseEntity<>("All Orders Deleted Successfully", HttpStatus.ACCEPTED);
	} 

}
