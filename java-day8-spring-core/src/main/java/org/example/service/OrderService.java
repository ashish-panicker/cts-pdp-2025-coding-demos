package org.example.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.discounts.DiscountPolicy;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.notification.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderService {

    private final DiscountPolicy discountPolicy;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    public OrderService(DiscountPolicy discountPolicy,
                        @Qualifier("cardPaymentService") PaymentService paymentService,
                        NotificationService notificationService) {
        this.discountPolicy = discountPolicy;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    @PostConstruct
    public void init() {
        System.out.println("[OrderService] - init()");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("[OrderService] - destroy()");
    }

    public void processOrder(Order order) {
        System.out.println("[OrderService] - processing order: " + order.getId());
        double total = order.getItems().stream().mapToDouble(OrderItem::getTotal).sum();
        System.out.println("[OrderService] - order total: " + total);
        var discountedTotal = discountPolicy.applyDiscount(order, total);
        order.setTotalAmount(discountedTotal);
        System.out.println("[OrderService] - discounted total: " + discountedTotal);
        var paid = paymentService.pay(order.getId(), discountedTotal);
        var message = "";
        if (paid) {
            message = "Your order " + order.getId() + " has been processed with amount : " + order.getTotalAmount();
        } else {
            message = "Failed to process your order: " + order.getId();
        }
        notificationService.notifyCustomer(order.getCustomer().getEmail(), message);
    }
}
