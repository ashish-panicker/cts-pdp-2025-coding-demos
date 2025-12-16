package com.example.springbootkafkademo.service;

import com.example.springbootkafkademo.event.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "order-events",
            groupId = "notification-group",
            concurrency = "3" // Spring will create 3 threads to read from th topic
    )
    public void consumeEvent(
            OrderCreatedEvent event,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
        System.out.printf(
                "Consumed Event: %s%n Partition: %d Offset: %d Thread: %s",
                event, partition, offset, Thread.currentThread().getName()
        );
        System.out.println();
    }
}
