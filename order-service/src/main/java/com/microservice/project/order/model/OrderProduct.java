package com.microservice.project.order.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_product_tb")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderProduct {
	
	@Id
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String productType;
	@Column(nullable = false)
	private BigDecimal price;
	@Column(nullable = false)
	private int range;
	@Column(nullable = false)
	private int quantity;

}
