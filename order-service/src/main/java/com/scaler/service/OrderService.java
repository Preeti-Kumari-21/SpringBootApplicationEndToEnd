package com.scaler.service;

import com.netflix.discovery.converters.Auto;
import com.scaler.client.UserClient;
import com.scaler.dto.OrderResponseDTO;
import com.scaler.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private UserClient userClient;

    public OrderResponseDTO createOrder(int userId){
        UserResponseDTO userResponseDTO = userClient.getUserById(userId);

        return new OrderResponseDTO(
                userResponseDTO.getId(),
                userResponseDTO.getName(),
                "Order created Successfully");
    }
}
