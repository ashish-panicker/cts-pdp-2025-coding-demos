package org.miniorm.model;

import org.miniorm.annotation.Column;
import org.miniorm.annotation.Entity;
import org.miniorm.annotation.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Column(name = "emp_id", primaryKey = true)
    private Long empId;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "emp_sal")
    private double salary;

    public Employee() {}

    public Employee(Long empId, String name, double salary) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
