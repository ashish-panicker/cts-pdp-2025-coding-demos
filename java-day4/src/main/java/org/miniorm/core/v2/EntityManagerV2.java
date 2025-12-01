package org.miniorm.core.v2;

import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.List;

public class EntityManagerV2 {
    public static List<Object> extractValues(Object entity, MetadataV2 metaData) {
        List<Object> values = new ArrayList<>();

        try {
            for (MethodHandle getter : metaData.getGetters().values()) {
                Object value = getter.invoke(entity);   // MethodHandle-based getter
                values.add(value);
            }

        } catch (Throwable t) {
            throw new RuntimeException(t);
        }

        return values;
    }
}
