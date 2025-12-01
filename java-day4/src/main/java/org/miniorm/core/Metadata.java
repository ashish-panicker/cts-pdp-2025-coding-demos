package org.miniorm.core;

import org.miniorm.annotation.Column;
import org.miniorm.annotation.Entity;
import org.miniorm.annotation.Table;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Metadata {

    private final String tableName;
    private String primaryKeyColumn;
    private final Map<String, Field> columnMap = new HashMap<>();

    public Metadata(Class<?> cls) {
        // Check for @Entity
        if(!cls.isAnnotationPresent(Entity.class)){
            throw new RuntimeException(cls.getName() + ": @Entity annotation must be present.");
        }

        // Table name from the @Table annotation
        Table table = cls.getAnnotation(Table.class);
        tableName = table.name();

        // Mapping Fields with @Column annotation
        Arrays.stream(cls.getDeclaredFields())
                .forEach(field -> { // Lambda block
                    // Assumption: Every field has @Column annotation applied.
                    if (field.isAnnotationPresent(Column.class)) {
                        Column column = field.getAnnotation(Column.class);
                        columnMap.put(column.name(), field);
                        if (column.primaryKey()) {
                            primaryKeyColumn = column.name();
                        }
                    }
                });
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public Map<String, Field> getColumnMap() {
        return columnMap;
    }
}
