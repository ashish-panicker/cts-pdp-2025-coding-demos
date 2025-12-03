package org.example.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class RideTypeFlyweight {

    private final static Map<String, RideType> CACHE = new HashMap<>();

    public static RideType getRideType(String type) {

        if (!CACHE.containsKey(type)) {
            switch (type) {
                case "GO" -> CACHE.put(type, new RideType("Uber GO", 100, 12));
                case "XL" -> CACHE.put(type, new RideType("Uber XL", 200, 20));
                case "AUTO" -> CACHE.put(type, new RideType("Uber AUTO", 50, 8));
            }
        }

        return CACHE.get(type);

    }
}
