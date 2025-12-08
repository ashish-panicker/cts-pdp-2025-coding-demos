package org.example.jpa.library;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lib_authors")
public class Author {

    @Id @GeneratedValue
    private Long id;

    private String fullName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
        book.setAuthor(this);
    }
}
