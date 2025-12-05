package org.example.repository;

import org.example.model.Order;

public record OrderParam(Order order, double expectedTotal) {
}
