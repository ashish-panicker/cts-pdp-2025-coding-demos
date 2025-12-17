package com.example.commandservice.controller;

import com.example.commandservice.model.OrderEntity;
import com.example.commandservice.model.OrderItemEntity;
import com.example.commandservice.model.OrderStatus;
import com.example.commandservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place-order")
    public String placeOrder() {
        var item1 = OrderItemEntity.builder()
                .productId(UUID.randomUUID().toString()).quantity(10).price(100.0)
                .build();
        var item2 = OrderItemEntity.builder()
                .productId(UUID.randomUUID().toString()).quantity(25).price(120.0)
                .build();
        var item3 = OrderItemEntity.builder()
                .productId(UUID.randomUUID().toString()).quantity(1).price(1000.0)
                .build();
        var order = OrderEntity.builder()
                .orderItemEntities(List.of(item1, item2, item3))
                .orderId(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .status(OrderStatus.PLACED)
                .creationDate(Instant.now())
                .build();
        item1.setOrderEntity(order);
        item2.setOrderEntity(order);
        item3.setOrderEntity(order);
        orderService.save(order);
        return "Order Placed";
    }
}

// com.producer.event.MyEvent, instance is serialized ans send to kafka topics
// com.consumer.event.MyEvent, needs to be de-serialized into this from kafka
