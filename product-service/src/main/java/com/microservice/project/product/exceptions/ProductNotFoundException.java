package com.microservice.project.product.exceptions;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String message) {
		super(message);
	}

}
