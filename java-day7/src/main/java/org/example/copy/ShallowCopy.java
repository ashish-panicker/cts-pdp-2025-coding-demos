package org.example.copy;

/* Shallow Copy
 * Create a new object
 * Does not copy the nested objects, only copy its references
 * Both original and copied object shares the same reference of the nested objects
 * Mutating the inner object will affect both
 */
public class ShallowCopy {
    public static void main(String[] args) throws CloneNotSupportedException {

        Person original = new Person("Ashish", new Address("Trivandrum"));
        Person cloned = (Person) original.clone();

        System.out.println("Printing details [Before changing nested object]");
        System.out.println("Original: " + original.address.city);
        System.out.println("Cloned: " + cloned.address.city);

        original.address.city = "Mumbai";
        // Changing the address in the original object affects the cloned object as well
        // because both share the same address object

        System.out.println("Printing details [After changing nested object]");
        System.out.println("Original: " + original.address.city);
        System.out.println("Cloned: " + cloned.address.city);

        cloned.address.city = "Bangalore";
        System.out.println("Printing details [After changing nested object again]");
        System.out.println("Original: " + original.address.city);
        System.out.println("Cloned: " + cloned.address.city);
    }
}
