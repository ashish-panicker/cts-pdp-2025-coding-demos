package com.example.springbootkafkademo.controller;

import com.example.springbootkafkademo.event.OrderCreatedEvent;
import com.example.springbootkafkademo.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ProducerService producerService;

    @PostMapping("place-order")
    public String placeOrder() {
        for (int i = 0; i < 100; i++) {

            var event = new OrderCreatedEvent(
                    "ORD-0" + i,
                    "ashish",
                    5000.0,
                    Instant.now()
            );
            producerService.publishEvent(event);
        }
        return "Order Placed";
    }
}
