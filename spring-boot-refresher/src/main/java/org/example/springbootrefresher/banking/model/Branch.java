package org.example.springbootrefresher.banking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branches")
@Getter @Setter @NoArgsConstructor
public class Branch extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;   // like IFSC

    @Column(nullable = false)
    private String name;

    private String address;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();

}
