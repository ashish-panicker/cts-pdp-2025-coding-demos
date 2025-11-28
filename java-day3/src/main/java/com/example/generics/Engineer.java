package com.example.generics;

public class Engineer extends Employee {

    public Engineer(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double processSalary(double percentage) {
        return (salary * percentage / 100) + 100;
    }
}
