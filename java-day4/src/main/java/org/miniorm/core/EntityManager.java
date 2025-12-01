package org.miniorm.core;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntityManager {

    public static List<Object> extractValues(Object entity, Metadata metadata) {
        List<Object> values = new ArrayList<>();
        try {
            for (var entry : metadata.getColumnMap().entrySet()) {
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
