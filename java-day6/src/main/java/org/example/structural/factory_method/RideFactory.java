package org.example.structural.factory_method;

public interface RideFactory {
    Ride createRide();
}

class UberAutoFactory implements  RideFactory {
    @Override
    public Ride createRide() {
        return new UberAuto();
    }
}

class UberGoFactory implements RideFactory {
    @Override
    public Ride createRide() {
        return new UberGo();
    }
}

class UberXLFactory implements RideFactory {
    @Override
    public Ride createRide() {
        return new UberXL();
    }
}
