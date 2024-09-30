package com.microservice.project.inventory.exceptions;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String message) {
		super(message);
	}

}
