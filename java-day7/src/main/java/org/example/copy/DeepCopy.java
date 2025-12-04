package org.example.copy;

public class DeepCopy {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person2 original = new Person2("Ashish", new Address2("Trivandrum"));
        Person2 cloned = (Person2) original.clone();

        System.out.println("Printing details [Before changing nested object]");
        System.out.println("Original: " + original.address.city);
        System.out.println("Cloned: " + cloned.address.city);

        original.address.city = "Mumbai";
        System.out.println("Printing details [After changing original.address]");
        System.out.println("Original: " + original.address.city);
        System.out.println("Cloned: " + cloned.address.city);

        cloned.address.city = "Bangalore";
        System.out.println("Printing details [After changing cloned.address]");
        System.out.println("Original: " + original.address.city);
        System.out.println("Cloned: " + cloned.address.city);
    }
}
