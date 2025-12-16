package com.example.springbootkafkademo.service;

import com.example.springbootkafkademo.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, OrderCreatedEvent> kafka;

    public void publishEvent(OrderCreatedEvent event){
        kafka.send(
                "order-events", // Topic
                event.orderId(), // Key
                event // Message
        );
    }

}
