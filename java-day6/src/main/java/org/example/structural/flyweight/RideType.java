package org.example.structural.flyweight;

// Sharing vehicle type object across millions ride requests
public class RideType {

    private final String name;
    private final double baseFare;
    private final double farePerKm;

    public RideType(String name, double baseFare, double farePerKm) {
        this.name = name;
        this.baseFare = baseFare;
        this.farePerKm = farePerKm;
        System.out.println("Created ride of type " + name);
    }

    public String getName() {
        return name;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public double getFarePerKm() {
        return farePerKm;
    }
}
