package com.microservice.project.order.dto;

import java.math.BigDecimal;

public record OrderResponse(Long id,
							String name, 
							String description, 
							String productType,
							BigDecimal price,
							int range,
							int quantity) {

}
