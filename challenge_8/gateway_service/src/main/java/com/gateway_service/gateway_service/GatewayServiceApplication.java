package com.gateway_service.gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
	    return new RestTemplateBuilder();
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/service1/**")
						.filters(f -> f
								.prefixPath("/api")
								.addResponseHeader("X-Powered-By", "DanSON Gateway Service"))
						.uri("http://localhost:8082"))
				.route(r -> r.path("/service2/**")
						.filters(f -> f
								.prefixPath("/api")
								.addResponseHeader("X-Powered-By", "DanSON Gateway Service"))
						.uri("http://localhost:8083"))
				.route(r -> r.path("/api/v1/**")
						.filters(f -> f
								// .prefixPath("/api")
								.addResponseHeader("X-Powered-By", "DanSON Gateway Service"))
						.uri("http://localhost:8080"))
				.build();
	}

}
