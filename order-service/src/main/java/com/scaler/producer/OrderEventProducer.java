package com.scaler.producer;

import com.scaler.dto.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    @Autowired
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "order-created-topic";

    public void publishOrderCreatedEvent(OrderCreatedEvent event){
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Published Event To Kafka");
    }
}
