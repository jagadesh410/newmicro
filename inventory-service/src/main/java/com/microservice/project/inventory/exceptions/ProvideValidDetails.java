package com.microservice.project.inventory.exceptions;

public class ProvideValidDetails extends RuntimeException {
	
	public String message;
	
	public ProvideValidDetails(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}

}
