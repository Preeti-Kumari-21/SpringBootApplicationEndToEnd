package com.scaler.consumer;

import com.scaler.dto.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedConsumer {

    @KafkaListener(
            topics = "order-created-topic",
            groupId = "notification-group"
    )

    public void consume(OrderCreatedEvent event) {
        System.out.println("==================================================");
        System.out.println("Order Event Received");
        System.out.println("User Name : "+event.getUserName());
        System.out.println("Product Name : "+event.getProductName());
        System.out.println("Product Price : "+event.getProductPrice());
        System.out.println("Sending order confirmation notification...");
        System.out.println("===================================================");
    }
}
