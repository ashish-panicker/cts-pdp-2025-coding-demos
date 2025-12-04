package org.example.copy;

// Deep copy
public class Person2 implements Cloneable {
    String name;
    Address2 address;

    public Person2(String name, Address2 address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        var person = (Person2) super.clone();
        person.address = (Address2) address.clone();
        return person;
    }
}

class Address2 implements Cloneable {
    String city;

    public Address2(String city) {
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Address2(this.city);
    }
}