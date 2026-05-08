package com.scaler.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDTO {

    private Integer id;

    private String name;

    private Double price;

    private Integer quantity;
}
