package org.example.jpa.elms;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "elms_employees",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elms_emp_sq")
    @SequenceGenerator(name = "elms_emp_sq", sequenceName = "elms_emp_sq", allocationSize = 50)
    private Long empId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Lob // CLOB
    private String bio;

    @Lob // BLOB
    private byte[] pic;

    private boolean isActive;

    @Transient
    private String note;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "state", column = @Column(name = "home_state")),
            @AttributeOverride(name = "street", column = @Column(name = "home_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zipCode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "mail_city")),
            @AttributeOverride(name = "state", column = @Column(name = "mail_state")),
            @AttributeOverride(name = "street", column = @Column(name = "mail_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "mail_zipCode"))
    })
    private Address mailingAddress;
}
