package com.scaler.controller;

import com.scaler.dto.PaymentRequestDTO;
import com.scaler.dto.PaymentResponseDTO;
import com.scaler.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public PaymentResponseDTO makePayment(@RequestBody PaymentRequestDTO requestDTO){
        return paymentService.processPayment(requestDTO);
    }
}
