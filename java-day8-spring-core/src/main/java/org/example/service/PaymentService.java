package org.example.service;

public interface PaymentService {

    boolean pay(String orderId, double amount);
}
