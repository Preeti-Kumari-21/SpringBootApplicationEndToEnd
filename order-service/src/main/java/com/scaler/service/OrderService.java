package com.scaler.service;

import com.netflix.discovery.converters.Auto;
import com.scaler.client.UserClient;
import com.scaler.dto.OrderResponseDTO;
import com.scaler.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class OrderService {
    @Autowired
    private UserClient userClient;

    @Retry(name="userService" , fallbackMethod = "userServiceFallback")
    @CircuitBreaker(name="userService")
    public OrderResponseDTO createOrder(int userId){
        System.out.println("Calling user-service for userId: " + userId);
        UserResponseDTO userResponseDTO = userClient.getUserById(userId);

        return new OrderResponseDTO(
                userResponseDTO.getId(),
                userResponseDTO.getName(),
                "Order created Successfully");
    }

    public OrderResponseDTO userServiceFallback(int userId , Throwable ex){
        return new OrderResponseDTO(
                userId,
                "Unavailable",
                "User Service is temporarily unavailable, please try again later"
        );
    }
}
