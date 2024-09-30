package com.microservice.project.order.exceptions;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String message) {
		super(message);
	}

}
