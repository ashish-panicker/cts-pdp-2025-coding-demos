package org.example.exceptions;

/*
 * SuppressedException
 * The close() method of a resource throws an exception and the try-resource block
 * you are using, an exception is being generated in the try block.
 */
public class SuppressedException {
    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            System.out.println("try resource block");
            throw new RuntimeException("Some exception in business code");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            for (var ex : e.getSuppressed()) {
                System.out.println("Suppressed: " + ex.getMessage());
            }
        }
    }
}

// If you want to use a resource in try-resource block, that resource has to be closable
class MyResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("MyResource closed.");
        throw new Exception("Some error occurred during close()");
    }
}