package org.example.service;

import org.example.service.config.CardPaymentProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("cardPaymentService")
// @Primary // Not needed if @Qualifier is being used
public class CardPaymentService implements PaymentService {

    @Value("${payment.card.gatewayUrl}")
    private String gatewayUrl;

    @Autowired
    private CardPaymentProps props;

    public CardPaymentService() {
        System.out.println("[CardPaymentService] - created.");
    }

    @Override
    public boolean pay(String orderId, double amount) {
        System.out.println("[CardPaymentService] - " + props);
        System.out.println("[CardPaymentService] - using " + gatewayUrl + " as gateway");
        System.out.println("[Card Payment]: Order id: " + orderId + ", Amount: " + amount);
        return true;
    }
}
