package com.microservice.project.inventory.service;

import java.util.List;

import com.microservice.project.inventory.dto.InventoryRequest;
import com.microservice.project.inventory.dto.InventoryResponse;

public interface InventoryService {
	
	List<InventoryResponse> getProducts();
	InventoryResponse getProductById(Long id);
	void addProduct(InventoryRequest inventoryRequest);
	InventoryResponse updateProduct(Long id, InventoryRequest inventoryRequest);
	InventoryResponse updateOrderProduct(Long id, int quantity);
	boolean deleteProduct(Long id);
	boolean deleteAllProducts();

}
