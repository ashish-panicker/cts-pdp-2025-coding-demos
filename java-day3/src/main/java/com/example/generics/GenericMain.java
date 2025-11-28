package com.example.generics;


import java.util.ArrayList;
import java.util.List;

public class GenericMain {

    // var x = 10; // var is local and cannot be used outside of its scope

    /**
     * ? means unknown type
     * ? extends T means T or any subtype of T
     * ? super T means T or any supertype of T
     */

    // PECS - Producer Extends Consumer Super

    // Type Erasure?
    // List<String> names = new ArrayList<>();
    // List<Integer> numbers = new ArrayList<>();

    // After type erasure
    // List names = new ArrayList();
    // List numbers = new ArrayList();

    // Reified Generics
    // Generic information is preserved at compile time and used in run time
    public static void main(String[] args) {

        Box<String> strBox = new Box<>("Hello");
        System.out.println(strBox.getValue());

        Box<Integer> intBox = new Box<>(1);
        System.out.println(intBox.getValue());

        Pair<String, Integer> pair = new Pair<>("Hello", 1);
        System.out.println(pair);

        var vPair = new Pair<>("Welcome", 2);
        System.out.println(vPair);

        print(10);
        print("Hello");

        System.out.println(print2(10));
        System.out.println(print2("Hello"));

        printNumber(10);
        printNumber(10.5);
        // printNumber("String");

        List<? extends Number> numbers = List.of(1, 2, 3);

        List<?> names;
        names = new ArrayList<String>();

        // This declaration accepts Integer, Number, Object
        List<? super Integer> integers = List.of(1, 2, 3);

        Number n = Integer.valueOf(10);
        Object o = Integer.valueOf(10);

        List<String> names2 = new ArrayList<>();
        names2.add("John");
        String x = names2.get(0);

        // List names2 = new ArrayList();
        // String x = (String) names2.get(0);
    }

    // Bounded Generics
    static <T extends Number> void printNumber(T num) {

    }

    static <T extends Employee> void printSalary(T emp) {
        // Any type of Employee can be passed as an argument to this method
        // IS-A

    }

    static <T> void print(T value) {
        System.out.println("Printing value: " + value + ".");
    }

    static <T> T print2(T value) {
        return value;
    }

    public void print() {
        // Local type inference
        var list = List.of(1, 2, 3);
        var x = 10;
        var name = "John";
        // var y = null; // cannot be null
        // var z; // must be initialized
    }


    static double sum(List<? extends Number> numbers) {
        return numbers.stream().mapToDouble(Number::doubleValue).sum();
    }
}


// public class Graph<N extends Comparable<N>, W extends Number> {}

class Data implements Comparable<Data> {

    @Override
    public int compareTo(Data o) {
        return 0;
    }
}