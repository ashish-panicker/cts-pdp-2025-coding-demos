package org.example.jpa.elms;

import jakarta.persistence.EntityManager;
import org.example.jpa.entity.JPAUtil;

public class Main {
    public static void main(String[] args) {
        try (EntityManager entityManager = JPAUtil.entityManager()) {
        }
        JPAUtil.shutdown();
    }
}
