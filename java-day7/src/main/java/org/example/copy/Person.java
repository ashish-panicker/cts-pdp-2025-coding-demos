package org.example.copy;

// Shallow copy
public class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Shallow copy
    }
}

class Address {
    String city;

    public Address(String city) {
        this.city = city;
    }
}