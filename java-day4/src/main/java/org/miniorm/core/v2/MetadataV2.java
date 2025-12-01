package org.miniorm.core.v2;

import org.miniorm.annotation.Column;
import org.miniorm.annotation.Entity;
import org.miniorm.annotation.Table;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class MetadataV2 {

    private final String tableName;
    private final String primaryKeyColumn;

    // Instead of storing Field, store fast-access MethodHandle getters
    // MethodHandle was introduced from jdk7
    // Supports JIT/JVM inlining
    // Faster upto 6 times
    // Uses invokedynamic [invokestatic, invokeinterface, invokevirtual]
    // invokedynamic aka indy, introduced in Java 7
    private final Map<String, MethodHandle> getters = new LinkedHashMap<>();

    public MetadataV2(Class<?> cls) throws IllegalAccessException {
        // Check for @Entity
        if(!cls.isAnnotationPresent(Entity.class)){
            throw new RuntimeException(cls.getName() + ": @Entity annotation must be present.");
        }

        // Table name from the @Table annotation
        Table table = cls.getAnnotation(Table.class);
        tableName = table.name();

        // MethodHandle: a typed, direct reference to a method/constructor/field that can be
        // called like a function pointer. Can be considered as a low level pointer used by Java.
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandles.Lookup privateLookup =
                MethodHandles.privateLookupIn(cls, lookup);
        AtomicReference<String> pk = new AtomicReference<>();

        // Mapping Fields with @Column annotation
        Arrays.stream(cls.getDeclaredFields())
                .forEach(field -> {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column column = field.getAnnotation(Column.class);
                        String columnName = column.name();
                        MethodHandle getter = null;
                        try {
                            getter = privateLookup.unreflectGetter(field);
                            getters.putIfAbsent(columnName, getter);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                        getters.put(columnName, getter);

                        if (column.primaryKey()) {
                            pk.set(columnName);
                        }
                    }
                });
        this.primaryKeyColumn = pk.get();
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public Map<String, MethodHandle> getGetters() {
        return getters;
    }
}
