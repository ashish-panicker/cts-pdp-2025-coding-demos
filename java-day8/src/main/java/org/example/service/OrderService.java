package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.example.util.DiscountUtil;

@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final DiscountUtil util;

    public Order createOrder(Order order) {
        double discount = util.calculateDiscount(order.calculateTotal());
        order.applyDiscount(discount);
        return repository.save(order);
    }

    public Order getOrder(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order id does not correspond to an actual order"));
    }
}
