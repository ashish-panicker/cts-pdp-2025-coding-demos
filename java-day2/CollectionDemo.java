import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CollectionDemo {
    public static void main(String[] args) {
        // ArrayList    -> Dynamic array implementation
        // LinkedList   -> Doubly linked list implementation
        // HashMap      -> Hash table implementation for unique elements
        //                 Array + Linked List 


        // Set does not allow duplicate elements
        // Set deos not maintain insertion order
        // Uses a HashMap internally
        Set<String> fruits = new HashSet<>(Set.of("Apple", "Banana", "Orange"));
        System.out.println(fruits);
        fruits.add("Mango");
        fruits.add("Mango");
        
        System.out.println(fruits);
        /**
         * private static final Object PRESENT = new Object();
         * 
         * HashMap<String, Object> map = new HashMap<>();
         * map.put("Apple", PRESENT);
         * map.put("Banana", PRESENT);
         * map.put("Orange", PRESENT);
         * map.put("Mango", PRESENT); 
         */

        // LinkedHashSet maintains insertion order
        Set<String> vegetables = new LinkedHashSet<>();
        vegetables.add("Carrot");
        vegetables.add("Potato");
        vegetables.add("Cabbage");
        System.out.println(vegetables);

        User user1 = new User();
        user1.id = 1;
        user1.name = "Alice";
        user1.isActive = true;

    }
}
class User {
    int id;
    String name;
    boolean isActive;
}

/**
 * 1. Object Header
 * 2. Instance Data
 * 3. Padding
 * 
 * Object Header:
 *  Mark Word (8 bytes):
 *  - Hashcode
 *  - GC Generation Age
 *  - Lock bits
 *  - Thread ID
 *  Klass Pointer:
 *  - Points to the class metadata in the Method Area
 * 
 * Instance Data: 
 *  Actual data stored in the object
 * 
 * Padding:
 *  Ensures that the object size is a multiple of 8 bytes for alignment
 */

// +XX:UseSerialGC