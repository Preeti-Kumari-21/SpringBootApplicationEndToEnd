package com.scaler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private int userId;
    private String userName;
    private Integer productId;
    private String productName;
    private Double productPrice;
    private String message;
}
