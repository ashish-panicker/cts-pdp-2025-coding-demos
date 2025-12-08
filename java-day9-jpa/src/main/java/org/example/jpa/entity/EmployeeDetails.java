package org.example.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // This is an Entity class
@Table(name = "emp_details") // Mapped to the table emp_details
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double salary;

    public void updateSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        }
    }
}
