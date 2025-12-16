package com.example.springbootkafkademo.event;

import java.time.Instant;

public record OrderCreatedEvent(String orderId, String userId, double amount, Instant createdAt) {
}
