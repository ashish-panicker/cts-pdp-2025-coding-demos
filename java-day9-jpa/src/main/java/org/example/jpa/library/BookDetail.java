package org.example.jpa.library;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lib_bookdetails")
public class BookDetail {
    @Id
    @GeneratedValue
    private Long id;
    private String isbn;
    private int pageCount;

    @OneToOne
    @MapsId // Share PK between Book and BookDetail
    @JoinColumn(name = "id")
    private Book book;
}
