package com.example;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class Introspector {
    public static void main(String[] args) {
        inspectClass(ArrayList.class);

    }

    static void inspectClass(Class<?> cls) {
        System.out.println("Class :" + cls.getName());
        System.out.println("Superclass :" + cls.getSuperclass());
        System.out.println("Interfaces :");
        Arrays.stream(cls.getInterfaces()).forEach(interfaceClass -> System.out.println("\t" + interfaceClass.getName()));
        System.out.println("Modifiers :" + Modifier.toString(cls.getModifiers()));
        System.out.println("Fields :");
        Arrays.stream(cls.getFields()).forEach(field ->
                System.out.println("\t" + Modifier.toString(field.getModifiers())
                    + " :" + field.getName() + " :" + field.getType().getSimpleName()
                    + " :" + field.getGenericType())
        );
    }
}
