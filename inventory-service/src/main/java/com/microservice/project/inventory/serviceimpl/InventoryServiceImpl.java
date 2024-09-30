package com.microservice.project.inventory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.project.inventory.dto.InventoryRequest;
import com.microservice.project.inventory.dto.InventoryResponse;
import com.microservice.project.inventory.exceptions.ProductAvailabilityException;
import com.microservice.project.inventory.exceptions.ProductNotFoundException;
import com.microservice.project.inventory.model.InventoryProduct;
import com.microservice.project.inventory.repository.InventoryRepository;
import com.microservice.project.inventory.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public List<InventoryResponse> getProducts() {
		// TODO Auto-generated method stub
		List<InventoryProduct> products = inventoryRepository.findAll();
		List<InventoryResponse> productsResponse = products.stream()
				.map(product -> new InventoryResponse(
						product.getId(),
						product.getName(), 
						product.getDescription(), 
						product.getProductType(), 
						product.getPrice(), 
						product.getRange(),
						product.getQuantity())
				).toList();		
		return productsResponse;
	}

	@Override
	public InventoryResponse getProductById(Long id) {
		// TODO Auto-generated method stub
		InventoryProduct product = inventoryRepository.findById(id).orElse(null);
		if(product != null) {
			return new InventoryResponse(
					id, 
					product.getName(), 
					product.getDescription(), 
					product.getProductType(),
					product.getPrice(), 
					product.getRange(),
					product.getQuantity());
		} else {
			throw new ProductNotFoundException("Product Not Found with ID : "+id);
		}
	}

	@Override
	public void addProduct(InventoryRequest inventoryRequest) {
		// TODO Auto-generated method stub
		InventoryProduct product = InventoryProduct.builder()
				.name(inventoryRequest.name())
				.description(inventoryRequest.description())
				.productType(inventoryRequest.productType())
				.price(inventoryRequest.price())
				.range(inventoryRequest.range())
				.quantity(inventoryRequest.quantity())
				.build();
		inventoryRepository.save(product);
	}

	@Override
	public InventoryResponse updateProduct(Long id, InventoryRequest inventoryRequest) {
		// TODO Auto-generated method stub
		InventoryProduct product = inventoryRepository.findById(id)
				.orElse(null);
		if(product != null) {
			product.setName(inventoryRequest.name());
			product.setDescription(inventoryRequest.description());
			product.setProductType(inventoryRequest.productType());
			product.setPrice(inventoryRequest.price());
			product.setRange(inventoryRequest.range());
			product.setQuantity(inventoryRequest.quantity());
			inventoryRepository.save(product);
			return new InventoryResponse(
					id, 
					product.getName(), 
					product.getDescription(), 
					product.getProductType(), 
					product.getPrice(), 
					product.getRange(),
					product.getQuantity());
		} else {
			throw new ProductNotFoundException("Product Not Found with ID : "+id);
		}
	}
	
	@Override
	public InventoryResponse updateOrderProduct(Long id, int quantity) {
		// TODO Auto-generated method stub
		InventoryProduct product = inventoryRepository.findById(id)
				.orElse(null);
		if(product != null && quantity <= product.getQuantity()) {
			int remainingInStock = product.getQuantity() - quantity;
			product.setQuantity(remainingInStock);
			inventoryRepository.save(product);
			return new InventoryResponse(
					id, 
					product.getName(), 
					product.getDescription(), 
					product.getProductType(), 
					product.getPrice(), 
					product.getRange(),
					product.getQuantity());
		} else if (product == null){
			throw new ProductNotFoundException("Product Not Found with ID : "+id);
		} else {
			throw new ProductAvailabilityException("Product Out Of Stock for Quantity : "+quantity);
		}

	}

	@Override
	public boolean deleteProduct(Long id) {
		// TODO Auto-generated method stub
		InventoryProduct product = inventoryRepository.findById(id)
				.orElse(null);
		if(product != null) {
			inventoryRepository.deleteById(id);
			return true;
		} else {
			throw new ProductNotFoundException("Product Not Found with ID : "+id);
		}
	}

	@Override
	public boolean deleteAllProducts() {
		// TODO Auto-generated method stub
		inventoryRepository.deleteAll();
		return false;
	}

}
