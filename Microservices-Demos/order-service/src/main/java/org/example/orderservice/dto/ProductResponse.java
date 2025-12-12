package org.example.orderservice.dto;

public record ProductResponse(Long id, String name, Integer stock, Double price) {
}
