package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

// Reflection:Ability of Java to inspect itself at runtime and change or modify
// behaviour at runtime.
public class ReflectionDemo {
    public static void main(String[] args) {

        // Core reflection API
        // Class: Class represents Classes and Interfaces
        Object obj = "Hello World";
        Class<?> cls = obj.getClass();
        System.out.println(cls.getName() + " :" + cls.getSimpleName() + " :" + cls.getSuperclass());

        Class<String> stringCls = String.class;
        System.out.println(stringCls.getName() + " :" + stringCls.getSimpleName() + " :" + stringCls.getSuperclass());

        Class<int[]>  intArrCls = int[].class;
        System.out.println(intArrCls.getName() + " :" + intArrCls.getSimpleName() + " :" + intArrCls.getSuperclass());


        // Inspecting class metadata
        System.out.println("Interfaces");
        Stream.of(cls.getInterfaces()).forEach(c -> System.out.println("\t" +c.getName() + " :" + c.getSimpleName()));

        System.out.println("Declared Fields");
        Field[] fields = cls.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> System.out.println("\t" +field.getName() + " :" + field.getType()));

        System.out.println("Declared Methods");
        Method[] methods = cls.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> System.out.println("\t" +method.getName() + " :" + method.getReturnType()));

        System.out.println("Declared Constructors");
        Constructor[] constructors =  cls.getDeclaredConstructors();
        if (constructors.length > 0) {
            Arrays.stream(constructors).forEach(constructor -> System.out.println("\t" +constructor.getName()
                    + " :" + Arrays.toString(constructor.getParameterTypes())));
        }

    }
}
