package com.scaler.controller;

import com.scaler.dto.ProductRequestDTO;
import com.scaler.dto.ProductResponseDTO;
import com.scaler.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Valid @RequestBody ProductRequestDTO requestDTO){
        return new ResponseEntity<>(
                productService.createProduct(requestDTO),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable int id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}/reduce")
    public ResponseEntity<ProductResponseDTO> reduceQuantity(@PathVariable int id){
        return ResponseEntity.ok(productService.reduceQuantity(id));
    }
}
