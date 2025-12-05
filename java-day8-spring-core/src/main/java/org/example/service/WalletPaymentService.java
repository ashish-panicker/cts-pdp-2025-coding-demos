package org.example.service;

import org.springframework.stereotype.Service;

@Service("walletPaymentService")
public class WalletPaymentService implements PaymentService {

    public WalletPaymentService() {
        System.out.println("[WalletPaymentService] - created.");
    }

    @Override
    public boolean pay(String orderId, double amount) {
        System.out.println("[Wallet Payment]: Order id: " + orderId + ", Amount: " + amount);
        return true;
    }
}
