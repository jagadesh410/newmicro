package com.microservice.project.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.project.inventory.dto.InventoryRequest;
import com.microservice.project.inventory.dto.InventoryResponse;
import com.microservice.project.inventory.exceptions.ProductFieldsException;
import com.microservice.project.inventory.exceptions.ProvideValidDetails;
import com.microservice.project.inventory.serviceimpl.InventoryServiceImpl;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryServiceImpl inventoryServiceImpl;
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getProducts() {
		List<InventoryResponse> response = inventoryServiceImpl.getProducts();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		InventoryResponse response = inventoryServiceImpl.getProductById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody InventoryRequest request) {
		try {
			inventoryServiceImpl.addProduct(request);
			return new ResponseEntity<>("Product Added Successfully", HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			throw new ProvideValidDetails("Please provide valid details");
		} catch (Exception ex) {
			throw new ProductFieldsException("Please provide below details");
		}
		
	}
	
	@PutMapping("/updateProductById/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody InventoryRequest request) {
		InventoryResponse response = inventoryServiceImpl.updateProduct(id, request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateOrderedProductById/{id}/{quantity}")
	public ResponseEntity<?> updateOrderedProductById(@PathVariable Long id, @PathVariable int quantity) {
		InventoryResponse response = inventoryServiceImpl.updateOrderProduct(id, quantity);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProductById/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
		boolean isProductDeleted = inventoryServiceImpl.deleteProduct(id);
		return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);		
	}
	
	@DeleteMapping("/deleteAllProducts")
	public ResponseEntity<?> deleteAllProducts() {
		inventoryServiceImpl.deleteAllProducts();
		return new ResponseEntity<>("All Products Deleted Successfully", HttpStatus.OK);
	}

}
