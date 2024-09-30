package com.microservice.project.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.project.order.dto.OrderRequest;

@FeignClient(name = "inventory-service", url="http://localhost:8081/inventory")
public interface InventoryClient {
	
	@PutMapping("/updateOrderedProductById/{id}/{quantity}")
	public ResponseEntity<?> updateOrderedProductById(@PathVariable Long id, @PathVariable int quantity); 

}
