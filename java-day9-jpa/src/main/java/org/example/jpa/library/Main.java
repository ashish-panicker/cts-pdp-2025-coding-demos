package org.example.jpa.library;

import jakarta.persistence.EntityManager;
import org.example.jpa.entity.JPAUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (EntityManager em = JPAUtil.entityManager()) {
            var tx = em.getTransaction();
            tx.begin();

            Author author = Author.builder().books(new ArrayList<>()).fullName("J K Rowling").build();
            Category category = Category.builder().books(new ArrayList<>()).name("Fantasy").build();

            Reader r1 = Reader.builder().fullName("Ashish").build();
            Reader r2 = Reader.builder().fullName("Arun").build();

            Book hp1 = Book.builder().readers(new HashSet<>()).title("Harry Potter 1").build();
            Book hp2 = Book.builder().readers(new HashSet<>()).title("Harry Potter 2").build();
            Book hp3 = Book.builder().readers(new HashSet<>()).title("Harry Potter 3").build();

            BookDetail detail1 = BookDetail.builder().isbn("123-1234567").pageCount(320).build();
            BookDetail detail2 = BookDetail.builder().isbn("123-1234568").pageCount(350).build();
            BookDetail detail3 = BookDetail.builder().isbn("123-1234569").pageCount(390).build();

            author.addBook(hp1);
            author.addBook(hp2);
            author.addBook(hp3);

            category.addBook(hp1);
            category.addBook(hp2);
            category.addBook(hp3);

            hp1.setBookDetail(detail1);
            hp2.setBookDetail(detail1);
            hp3.setBookDetail(detail1);

            hp1.getReaders().add(r1);
            hp2.getReaders().add(r1);
            hp3.getReaders().add(r1);

            hp1.getReaders().add(r2);
            hp2.getReaders().add(r2);
            hp3.getReaders().add(r2);

            detail1.setBook(hp1);
            detail2.setBook(hp2);
            detail3.setBook(hp3);

            em.persist(author);
            em.persist(category);
            em.persist(r1);
            em.persist(r2);
            tx.commit();

            tx = em.getTransaction();
            tx.begin();

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Fetching Books");

            List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class)
                    .getResultList();
            for (Book b : books) {
                System.out.println("Book: " + b.getTitle());
                System.out.println("Author: " + b.getAuthor().getFullName());
            }
            tx.commit();

        }
        JPAUtil.shutdown();
    }
}
