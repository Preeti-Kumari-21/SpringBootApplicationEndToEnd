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

    if(requestDTO.getAmount() >= 100000){
        System.out.println("Payment Failed as the amount is greater than or equal to 100000");
        return new PaymentResponseDTO(
                0,
                "FAILED",
                requestDTO.getAmount()
        );
      }
    System.out.println("Payment Successfull...");

    return new PaymentResponseDTO(
            101,
            "SUCCESS",
            requestDTO.getAmount()
     );
    }
}
