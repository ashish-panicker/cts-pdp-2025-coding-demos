public class StringDemo {
    public static void main(String[] args) {
        /**
         * String class is immutable, representing a sequence of characters.
         * Uses UTF-16 encoding internally.
         */

        String s1 = "Hello"; // String Literal
        String s2 = "Hello"; // Points to the same object as s1
        String s3 = new String("Hello");
        String s4 = new String("Hello").intern();

        /**
         * Pool: Hello -> s1, s2, s4
         * Heap: s3
         */

        System.out.println(s1 == s2);// True
        System.out.println(s1 == s3);// False
        System.out.println(s1.equals(s3));// True
        System.out.println(s3 == s4);// False
        System.out.println(s1 == s4);// True
        System.out.println(s3.equals(s4));// True

        /**
         * Memory Layout of String Object:
         * Mark Word (lock, hashcode, GC info)
         * Klass Pointer (points to String class metadata)
         * Instance Data:
         * - int hash
         * - byte[] value
         * - byte coder (indicates UTF-16 or LATIN1)
         */

        String s = null;
        System.out.println(s + " Java");

        System.out.println(new StringBuilder()
                .append(s) // <-- append(null) handled safely
                .append(" Java")
                .toString());

    }
}
