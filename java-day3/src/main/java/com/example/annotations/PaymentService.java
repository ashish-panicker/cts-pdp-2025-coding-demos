package com.example.annotations;

public class PaymentService {

    @LogExecution
    public void processPayment() throws InterruptedException {
        System.out.println("Processing payment...");
        Thread.sleep(1000);
        System.out.println("Payment processed successfully!");
    }
}
