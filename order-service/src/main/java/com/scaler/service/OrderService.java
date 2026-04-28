package com.scaler.service;

import com.netflix.discovery.converters.Auto;
import com.scaler.client.UserClient;
import com.scaler.dto.OrderResponseDTO;
import com.scaler.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {
    @Autowired
    private UserClient userClient;

    @CircuitBreaker(name="userService", fallbackMethod = "userServiceFallback")
    public OrderResponseDTO createOrder(int userId){
        UserResponseDTO userResponseDTO = userClient.getUserById(userId);

        return new OrderResponseDTO(
                userResponseDTO.getId(),
                userResponseDTO.getName(),
                "Order created Successfully");
    }

    public OrderResponseDTO userServiceFallback(int userId , Exception ex){
        return new OrderResponseDTO(
                userId,
                "Unavailable",
                "User Service is temporarily unavailable, please try again later"
        );
    }
}
