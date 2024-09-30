package com.microservice.project.product.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.project.product.dto.ProductRequest;
import com.microservice.project.product.dto.ProductResponse;
import com.microservice.project.product.exceptions.ProductNotFoundException;
import com.microservice.project.product.model.Product;
import com.microservice.project.product.repository.ProductRepository;
import com.microservice.project.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductResponse> getProducts() {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.findAll();
		List<ProductResponse> productsResponse = products.stream()
				.map(product -> new ProductResponse(
						product.getId(),
						product.getName(), 
						product.getDescription(), 
						product.getType(), 
						product.getPrice(), 
						product.getRange())
				).toList();		
		return productsResponse;
	}

	@Override
	public ProductResponse getProductById(Long id) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id).orElse(null);
		if(product != null) {
			ProductResponse response = ProductResponse.builder()
					.id(id)
					.name(product.getName())
					.description(product.getDescription())
					.type(product.getType())
					.price(product.getPrice())
					.range(product.getRange())
					.build();
			return response;
		} else {
			throw new ProductNotFoundException("Product Not Found with ID : "+id);
		}
		
	}

	@Override
	public void addProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		Product product = Product.builder()
							.name(productRequest.name())
							.description(productRequest.description())
							.type(productRequest.type())
							.price(productRequest.price())
							.range(productRequest.range())
							.build();
		productRepository.save(product);
	}

	@Override
	public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id)
				.orElse(null);
		if(product != null) {
			product.setName(productRequest.name());
			product.setDescription(productRequest.description());
			product.setType(productRequest.type());
			product.setPrice(productRequest.price());
			product.setRange(productRequest.range());		
			ProductResponse productResponse = ProductResponse.builder()
					.id(id)
					.name(productRequest.name())
					.description(productRequest.description())
					.type(productRequest.type())
					.price(productRequest.price())
					.range(productRequest.range())
					.build();
			productRepository.save(product);
			return productResponse;
		} else {
			throw new ProductNotFoundException("Product Not Found with ID : "+id);
		}
		
		
	}

	@Override
	public boolean deleteProduct(Long id) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id)
				.orElse(null);
		if(product != null) {
			productRepository.deleteById(id);
			return true;
		} else {
			throw new ProductNotFoundException("Product Not Found with ID : "+id);
		}
	}

	@Override
	public boolean deleteAllProducts() {
		// TODO Auto-generated method stub
		productRepository.deleteAll();
		return true;
	}

}
