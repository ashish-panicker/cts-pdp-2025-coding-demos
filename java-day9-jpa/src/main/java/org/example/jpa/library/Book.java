package org.example.jpa.library;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lib_books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    // Many Books -> One Author
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    // Many Category -> One Book
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // One Book -> One BookDetail
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private BookDetail bookDetail;

    @ManyToMany
    @JoinTable(
            name = "books_borrowers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "reader_id")
    )
    private Set<Reader> readers;

}
