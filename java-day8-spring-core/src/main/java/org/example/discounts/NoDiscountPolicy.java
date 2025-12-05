package org.example.discounts;

import org.example.model.Order;
import org.springframework.stereotype.Component;

@Component("noDiscount")
public class NoDiscountPolicy implements DiscountPolicy {

    @Override
    public double applyDiscount(Order order, double amount) {
        return amount;
    }
}
