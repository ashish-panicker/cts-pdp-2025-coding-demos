package org.example.structural.decorator;

/*
 * Decorator pattern allows us to implement dynamic features to an object
 * Fare in uber is based on
 * - Base fare
 * - Fare per km
 * - Surge pricing
 * - Toll Pricing
 */
public interface Fare {
    double cost();
}

class UberGoFare implements Fare {
    @Override
    public double cost() {
        return 100;
    }
}

class UberXLFare implements Fare {
    @Override
    public double cost() {
        return 200;
    }
}

class SurgeFareDecorator implements Fare {

    private final Fare fare;

    SurgeFareDecorator(Fare fare) {
        this.fare = fare;
    }

    @Override
    public double cost() {
        return fare.cost() * 1.2;
    }
}

class TollFareDecorator implements Fare {

    private final Fare fare;

    TollFareDecorator(Fare fare) {
        this.fare = fare;
    }

    @Override
    public double cost() {
        return fare.cost() + 25;
    }
}