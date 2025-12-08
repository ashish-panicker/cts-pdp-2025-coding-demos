package org.example.jpa.library;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lib_readers")
public class Reader {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;

    @ManyToMany(mappedBy = "readers")
    private Set<Book> books = new HashSet<>();
}
