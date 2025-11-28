package com.example.generics;

import java.io.Serializable;

// Multiple type bounds, upper bound is a class
public class NumberBox<T extends Number & Serializable> {

    private T value;

    public NumberBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

/**
 * public class NumberBox<Number> {
 *     private Number value;
 * }
 */
