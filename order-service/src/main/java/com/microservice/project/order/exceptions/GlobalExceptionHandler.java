package com.microservice.project.order.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handledProductNotFoundException(ProductNotFoundException ex, WebRequest web) {
		CustomErrorResponse customError = CustomErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND)
				.message(ex.getMessage())
				.path(web.getDescription(false))
				.build();
		return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductFieldsException.class)
	public ResponseEntity<ProductErrorResponse> handledProductFieldsException(ProductFieldsException ex, WebRequest web) {
		ProductErrorResponse customError = ProductErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST)
				.message(ex.getMessage())
				.details("{ name:String, description:String, type:String, price:BigDecimal, range:int }")
				.path(web.getDescription(false))
				.build();
		return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductAvailabilityException.class)
	public ResponseEntity<ProductErrorResponse> handledProductAvailabilityException(ProductAvailabilityException ex, WebRequest web) {
		ProductErrorResponse customError = ProductErrorResponse.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST)
				.message(ex.getMessage())
				.details("Selected quantity is not available in stock")
				.path(web.getDescription(false))
				.build();
		return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
	}
	

}
