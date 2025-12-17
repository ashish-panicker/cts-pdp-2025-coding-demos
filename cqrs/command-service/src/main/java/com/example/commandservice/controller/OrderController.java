package com.example.commandservice.controller;

import com.example.commandservice.model.Order;
import com.example.commandservice.model.OrderItem;
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
        var item1 = OrderItem.builder()
                .productId(UUID.randomUUID().toString()).quantity(10).price(100.0)
                .build();
        var item2 = OrderItem.builder()
                .productId(UUID.randomUUID().toString()).quantity(25).price(120.0)
                .build();
        var item3 = OrderItem.builder()
                .productId(UUID.randomUUID().toString()).quantity(1).price(1000.0)
                .build();
        var order = Order.builder()
                .orderItems(List.of(item1, item2, item3))
                .orderId(UUID.randomUUID().toString())
                .customerId(UUID.randomUUID().toString())
                .status(OrderStatus.PLACED)
                .creationDate(Instant.now())
                .build();
        item1.setOrder(order);
        item2.setOrder(order);
        item3.setOrder(order);
        orderService.save(order);
        return "Order Placed";
    }
}

// com.producer.event.MyEvent, instance is serialized ans send to kafka topics
// com.consumer.event.MyEvent, needs to be de-serialized into this from kafka
