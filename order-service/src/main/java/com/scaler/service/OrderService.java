package com.scaler.service;

import com.netflix.discovery.converters.Auto;
import com.scaler.client.ProductClient;
import com.scaler.client.UserClient;
import com.scaler.dto.OrderCreatedEvent;
import com.scaler.dto.OrderResponseDTO;
import com.scaler.dto.ProductResponseDTO;
import com.scaler.dto.UserResponseDTO;
import com.scaler.producer.OrderEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@Service
public class OrderService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderEventProducer orderEventProducer;

    @RateLimiter(name="orderService",fallbackMethod = "rateLimiterFallback")
    @Retry(name="userService" , fallbackMethod = "userServiceFallback")
    @CircuitBreaker(name="userService")
    public OrderResponseDTO createOrder(int userId, int productId){
        System.out.println("Calling user-service for userId: " + userId);
        UserResponseDTO userResponseDTO = userClient.getUserById(userId);

        ProductResponseDTO productResponseDTO = productClient.getProductById(productId);

        if(productResponseDTO.getQuantity() <= 0){
            throw new RuntimeException("Product out of stock");
        }

        ProductResponseDTO updatedProduct = productClient.reduceQuantity(productId);


        OrderCreatedEvent event = new OrderCreatedEvent(
                userResponseDTO.getId(),
                userResponseDTO.getName(),
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                "Order Created Successfully"
        );

        orderEventProducer.publishOrderCreatedEvent(event);
        System.out.println("Sending Event To Kafka...");


        return new OrderResponseDTO(
                userResponseDTO.getId(),
                userResponseDTO.getName(),
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                "Order created Successfully");
    }

    public OrderResponseDTO userServiceFallback(int userId , int productId, Throwable ex){
        System.out.println("Calling user-service...");
        return new OrderResponseDTO(
                userId,
                "Unavailable",
                productId,
                "Unavailable",
                0.0,
                "User Service is temporarily unavailable, please try again later"
        );
    }

    public OrderResponseDTO rateLimiterFallback(int userId , int productId, Throwable ex){
        System.out.println("Rate limiter triggered");
        return new OrderResponseDTO(
                userId,
                "Blocked",
                productId,
                "Blocked",
                0.0,
                "Too many requests. Please try again later."
        );
    }
}
