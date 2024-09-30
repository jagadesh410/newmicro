package com.microservice.project.api.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {
	
	@Bean
	public RouterFunction<ServerResponse> productServiceGetRoute() {
		return GatewayRouterFunctions.route("product-service")
				.route(RequestPredicates.path("/product/**"), HandlerFunctions.http("http://product-service"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> inventoryServiceRoute() {
		return GatewayRouterFunctions.route("inventory-service")
				.route(RequestPredicates.path("/inventory/**"), HandlerFunctions.http("http://localhost:8081"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> orderServiceRoute() {
		return GatewayRouterFunctions.route("order-service")
				.route(RequestPredicates.path("/order/**"), HandlerFunctions.http("http://order-service:8082"))
				.build();
	}

}
