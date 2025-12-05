package org.example.discounts;

import org.example.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Any class which is annotated with a stereo type annotation will be automatically picked
// up by the IOC container and a bean for that will be created.

// @Primary means, spring container will prefer this bean when there are multiple
// instances of DiscountPolicy
@Component("percentDiscount")
@Primary
public class PercentDiscountPolicy implements DiscountPolicy {

    @Value("${discount.percent}")
    private double discountPercent;

    @Override
    public double applyDiscount(Order order, double amount) {
        System.out.println("[DiscountPolicy] - Discount of " + (discountPercent * 100) + " percent applied");
        return amount - (amount * discountPercent);
    }
}
