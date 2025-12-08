package org.example.jpa.elms;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.jpa.entity.JPAUtil;

public class Main {
    public static void main(String[] args) {
        try (EntityManager em = JPAUtil.entityManager()) {
            EntityTransaction tx = em.getTransaction();

            tx.begin();

            Address addr = new Address("Street 1", "MG Road", "Bangalore", "560001");
            byte[] photo = new byte[] { 1, 2, 3, 4 }; // dummy BLOB

            Employee emp = new Employee(
                    "Ashish Panicker",
                    "ashish@example.com",
                    addr,
                    Status.ACTIVE,
                    "Senior trainer and consultant from India",
                    photo,
                    true // converted to 'Y'
            );

            em.persist(emp);

            tx.commit();

            System.out.println("Employee saved with ID: " + emp.getId());

            // Read
            Employee found = em.find(Employee.class, emp.getId());
            System.out.println("Found: " + found.getFullName() + " | " + found.getStatus());

            em.close();
        }
        JPAUtil.shutdown();
    }
}
