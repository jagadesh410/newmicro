package com.microservice.project.order.dto;

import java.math.BigDecimal;

public record OrderRequest(	String name, 
							String description, 
							String productType,
							BigDecimal price,
							int range,
							int quantity) {

}
