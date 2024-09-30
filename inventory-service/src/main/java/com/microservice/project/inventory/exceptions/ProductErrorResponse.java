package com.microservice.project.inventory.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductErrorResponse {
	
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private String details;
	private String path;
	@Override
	public String toString() {
		return "ProductErrorResponse [timestamp=" + timestamp + ", status=" + status + ", message=" + message
				+ ", details=" + details + ", path=" + path + "]";
	}
	
	

}
