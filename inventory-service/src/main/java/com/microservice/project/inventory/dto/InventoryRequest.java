package com.microservice.project.inventory.dto;

import java.math.BigDecimal;

public record InventoryRequest(	String name, 
								String description, 
								String productType,
								BigDecimal price,
								int range,
								int quantity) {

}
