package com.scaler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private int userId;
    private String userName;
    private int productId;
    private String productName;
    private double productPrice;
    private String message;
}
