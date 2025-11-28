package com.example.annotations;

public class Main {
    public static void main(String[] args) throws Exception {
        PaymentService paymentService = new PaymentService();
        LogExecutionProcessor.process(paymentService);
    }
}
