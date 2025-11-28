package com.example.generics;

public class Box<E> {

    private E value;

    public Box(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}

/**
 * After Type Erasure:
 * public class Box {
 *     private Object value
 * }
 */
