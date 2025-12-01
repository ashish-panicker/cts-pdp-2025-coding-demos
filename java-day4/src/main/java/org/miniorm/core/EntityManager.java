package org.miniorm.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    /**
     * public int add(int a, int b) {
     *      return a + b;
     * }
     * JVM inlining
     * int sum = add(a,b);
     * int sum = a + b; -> JVM inlining
     */

    public static List<Object> extractValues(Object entity, Metadata metadata) {
        List<Object> values = new ArrayList<>();
        try {
            for (var entry : metadata.getColumnMap().entrySet()) {
                // Reflection using Field is slower
                // - Security Checks
                // - No JVM inlining
                // - Indirection through FieldAccessor classes, meaning not directly accessing the fields
                //   but via FieldAccessor or MethodAccessor classes.
                Field field = entry.getValue();
                field.setAccessible(true);
                values.add(field.get(entity));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return values;
    }
}
