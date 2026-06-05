package com.scaler.service;

import com.scaler.dto.PaymentRequestDTO;
import com.scaler.dto.PaymentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentResponseDTO processPayment(PaymentRequestDTO requestDTO){

        System.out.println("Processing Payment...");
        System.out.println("User Id : "+requestDTO.getUserId());
        System.out.println("Amount : "+requestDTO.getAmount());

        return new PaymentResponseDTO(
                101,
                "SUCCESS",
                requestDTO.getAmount()
        );
    }
}
