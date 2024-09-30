package com.microservice.project.product.service;

import java.util.List;
import java.util.Optional;

import com.microservice.project.product.dto.ProductRequest;
import com.microservice.project.product.dto.ProductResponse;

public interface ProductService {
	
	List<ProductResponse> getProducts();
	ProductResponse getProductById(Long id);
	void addProduct(ProductRequest productRequest);
	ProductResponse updateProduct(Long id, ProductRequest productRequest);
	boolean deleteProduct(Long id);
	boolean deleteAllProducts();

}
