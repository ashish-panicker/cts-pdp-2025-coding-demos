package com.example.generics;

public class Employee {

    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double processSalary(double percentage) {
        return salary * percentage / 100;
    }
}
