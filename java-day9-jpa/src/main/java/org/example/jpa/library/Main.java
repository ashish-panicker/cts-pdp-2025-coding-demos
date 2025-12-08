package org.example.jpa.library;

import jakarta.persistence.EntityManager;
import org.example.jpa.entity.JPAUtil;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        try (EntityManager em = JPAUtil.entityManager()) {
            var tx = em.getTransaction();
            tx.begin();

            Author author = Author.builder().books(new ArrayList<>()).fullName("J K Rowling").build();
            Category category = Category.builder().books(new ArrayList<>()).name("Fantasy").build();
            Book hp1 = Book.builder().readers(new HashSet<>()).title("Harry Potter 1").build();
            BookDetail detail = BookDetail.builder().isbn("123-1234567").pageCount(320)
                                .build();
            Reader r1 = Reader.builder().fullName("Ashish").build();
            Reader r2 = Reader.builder().fullName("Arun").build();

            author.addBook(hp1);
            category.addBook(hp1);
            hp1.setBookDetail(detail);
            hp1.getReaders().add(r1);
            hp1.getReaders().add(r2);
            detail.setBook(hp1);

            em.persist(author);
            em.persist(category);
            em.persist(r1);
            em.persist(r2);

            tx.commit();
        }
        JPAUtil.shutdown();
    }
}
