package org.example.jpa.payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.jpa.entity.JPAUtil;

public class Main {
    public static void main(String[] args) {
        try (EntityManager em = JPAUtil.entityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            CashPayment cash = new CashPayment();
            cash.setAmount(2000.0);
            cash.setCashierName("Ashish");

            CardPayment card = new CardPayment();
            card.setAmount(1800.0);
            card.setCardNumber("1234567896321785");

            em.persist(cash);
            em.persist(card);

            tx.commit();
        }
        JPAUtil.shutdown();
    }
}
