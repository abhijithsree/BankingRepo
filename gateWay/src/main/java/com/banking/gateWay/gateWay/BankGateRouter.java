package com.banking.gateWay.gateWay;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankGateRouter {

	
	 @Bean
	    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
	        return builder.routes()
	                .route(r -> r.path("/users/**")
	                        .uri("lb://BANK-USER-SERVICE")
	                        .id("userModule"))
	                .route(r -> r.path("/auth/**")
	                        .uri("lb://BANK-AUTH-SERVICE")
	                        .id("authModule"))
	                .route(r -> r.path("/financeTransaction/**")
	                        .uri("lb://BANK-TRANSACTION-SERVICE")
	                        .id("finanacemodule"))
	                .route(r -> r.path("/viewBalance/**")
	                        .uri("lb://BANK-BALANCE-SERVICE")
	                        .id("balancemodule"))
	                .build();
	    }

}
