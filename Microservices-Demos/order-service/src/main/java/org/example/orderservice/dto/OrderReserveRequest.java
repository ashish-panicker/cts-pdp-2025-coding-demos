package org.example.orderservice.dto;

public record OrderReserveRequest(Long productId, Integer quantity) {
}
