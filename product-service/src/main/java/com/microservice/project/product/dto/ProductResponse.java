package com.microservice.project.product.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record ProductResponse(	Long id,
								String name, 
								String description, 
								String type,
								BigDecimal price,
								int range) {

}
