package com.example.annotations;

import java.lang.reflect.Method;

public class LogExecutionProcessor {
    public static void process(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LogExecution.class)) {
                long start = System.currentTimeMillis();
                method.invoke(obj);
                long end = System.currentTimeMillis();
                System.out.println("[LOG] Method " + method.getName() + " executed in " + (end - start) + " ms");
                System.out.println("------------------------------------");
            }
        }
    }
}
