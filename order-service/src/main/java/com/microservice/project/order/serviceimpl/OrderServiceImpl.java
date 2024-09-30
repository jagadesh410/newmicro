package com.microservice.project.order.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.project.order.client.InventoryClient;
import com.microservice.project.order.dto.OrderRequest;
import com.microservice.project.order.dto.OrderResponse;
import com.microservice.project.order.model.OrderProduct;
import com.microservice.project.order.repository.OrderRepository;
import com.microservice.project.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private InventoryClient inventoryClient;

	@Override
	public List<OrderResponse> getOrders() {
		// TODO Auto-generated method stub
		List<OrderProduct> products = orderRepository.findAll();
		List<OrderResponse> response = products.stream()
				.map(product -> new OrderResponse(
						product.getId(), 
						product.getName(), 
						product.getDescription(), 
						product.getProductType(), 
						product.getPrice(), 
						product.getRange(),
						product.getQuantity())
						).toList();
		return response;
	}

	@Override
	public boolean placeOrder(Long id, int quantity) {
		// TODO Auto-generated method stub
		ResponseEntity<?> orderedProduct = inventoryClient.updateOrderedProductById(id, quantity);
		if(orderedProduct != null) {
			ObjectMapper objectMapper = new ObjectMapper();

			OrderProduct orderProduct = objectMapper.convertValue(orderedProduct.getBody(), OrderProduct.class);
//			OrderProduct product = OrderProduct.builder()
//					.id(id)
//					.name(orderedProduct)
//					.description(orderRequest.description())
//					.vehicleType(orderRequest.vehicleType())
//					.price(orderRequest.price())
//					.range(orderRequest.range())
//					.build();
			orderRepository.save(orderProduct);
			return true;
		} else 
			return false;
	}

	@Override
	public boolean deleteOrder(Long id) {
		// TODO Auto-generated method stub
		OrderProduct product = orderRepository.findById(id).orElse(null);
		if(product != null) {
			orderRepository.deleteById(id);
			return true;
		} else
			return false;
	}

	@Override
	public boolean deleteAllOrder() {
		// TODO Auto-generated method stub
		orderRepository.deleteAll();
		return true;
	}

}
