package com.microservice.project.product.controller;

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

import com.microservice.project.product.dto.ProductRequest;
import com.microservice.project.product.dto.ProductResponse;
import com.microservice.project.product.exceptions.ProductFieldsException;
import com.microservice.project.product.exceptions.ProductNotFoundException;
import com.microservice.project.product.exceptions.ProvideValidDetails;
import com.microservice.project.product.serviceimpl.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getProducts() {
		List<ProductResponse> response = productServiceImpl.getProducts();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		ProductResponse response = productServiceImpl.getProductById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody ProductRequest request) {
		try {
			productServiceImpl.addProduct(request);
			return new ResponseEntity<>("Product Added Successfully", HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			throw new ProvideValidDetails("Please provide valid details");
		} catch (Exception ex) {
			throw new ProductFieldsException("Please provide below details");
		}
		
	}
	
	@PutMapping("/updateProductById/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
		ProductResponse response = productServiceImpl.updateProduct(id, request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProductById/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
		boolean isProductDeleted = productServiceImpl.deleteProduct(id);
		return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);		
	}
	
	@DeleteMapping("/deleteAllProducts")
	public ResponseEntity<?> deleteAllProducts() {
		productServiceImpl.deleteAllProducts();
		return new ResponseEntity<>("All Products Deleted Successfully", HttpStatus.OK);
	}

}
