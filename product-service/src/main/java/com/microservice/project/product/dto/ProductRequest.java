package com.microservice.project.product.dto;

import java.math.BigDecimal;

public record ProductRequest(	String name, 
								String description, 
								String type,
								BigDecimal price,
								int range) {

}
