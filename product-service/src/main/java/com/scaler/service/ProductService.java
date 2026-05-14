package com.scaler.service;

import com.scaler.dto.ProductRequestDTO;
import com.scaler.dto.ProductResponseDTO;
import com.scaler.exception.OutOfStockException;
import com.scaler.exception.ProductNotFoundException;
import com.scaler.pojo.Product;
import com.scaler.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO){
        Product product = Product.builder()
                .name(requestDTO.getName())
                .price(requestDTO.getPrice())
                .quantity(requestDTO.getQuantity())
                .build();

        Product savedProduct = productRepository.save(product);

        return ProductResponseDTO.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .price(savedProduct.getPrice())
                .quantity(savedProduct.getQuantity())
                .build();
    }

    public ProductResponseDTO getProductById(int id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public ProductResponseDTO reduceQuantity(int id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        if(product.getQuantity() <= 0){
            throw new OutOfStockException("Out of Stock");
        }

        product.setQuantity(product.getQuantity() - 1);

        Product updatedProduct = productRepository.save(product);

        return ProductResponseDTO.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .price(updatedProduct.getPrice())
                .quantity(updatedProduct.getQuantity())
                .build();
    }
}
