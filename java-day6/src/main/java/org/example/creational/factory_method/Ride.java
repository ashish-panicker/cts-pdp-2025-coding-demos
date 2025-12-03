package org.example.creational.factory_method;

public interface Ride {
    double calculateFare(double distance);

    String vehicleType();
}

class UberAuto implements Ride {
    @Override
    public double calculateFare(double distance) {
        return 8 + distance + 7;
    }

    @Override
    public String vehicleType() {
        return "Auto";
    }
}

class UberXL implements Ride {

    @Override
    public double calculateFare(double distance) {
        return 55 + distance * 15;
    }

    @Override
    public String vehicleType() {
        return "6/7 MPV";
    }
}

class UberGo implements Ride {
    @Override
    public double calculateFare(double distance) {
        return 40 + distance * 10;
    }

    @Override
    public String vehicleType() {
        return "Hatchback";
    }
}
