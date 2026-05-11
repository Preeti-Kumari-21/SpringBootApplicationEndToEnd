package com.scaler.controller;

import com.scaler.dto.OrderResponseDTO;
import com.scaler.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}/{productId}")
    public OrderResponseDTO createOrder(@PathVariable int userId , @PathVariable int productId){
        return orderService.createOrder(userId , productId);
    }
}
