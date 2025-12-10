package org.example.springbootsecuritybasics.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "corp_employees")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String role;
    private boolean isEnabled = true;

}
