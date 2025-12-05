package org.example.discounts;

import org.example.model.Order;

public interface DiscountPolicy {
    double applyDiscount(Order order, double amount);
}
