package com.microservice.project.product.exceptions;

public class ProvideValidDetails extends RuntimeException {
	
	public String message;
	
	public ProvideValidDetails(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}

}
