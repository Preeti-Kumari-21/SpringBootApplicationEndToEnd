package com.scaler.client;

import com.scaler.dto.PaymentRequestDTO;
import com.scaler.dto.PaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/payments")
    PaymentResponseDTO makePayment(
            @RequestBody PaymentRequestDTO requestDTO
    );
}
