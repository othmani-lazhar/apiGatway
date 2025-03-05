package com.java.apigatway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ConditionalRemoveOriginHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // Check if request is from the custom RIA IdP (e.g., based on a custom header)
//        ServerHttpRequest modifiedRequest = request.mutate()
//                .headers(httpHeaders -> httpHeaders.remove("Origin")) // Remove only if condition met
//                .build();
        return chain.filter(exchange.mutate().request(request).build());

    }

    @Override
    public int getOrder() {
        return -1; // Ensure it runs early in the filter chain
    }
}

