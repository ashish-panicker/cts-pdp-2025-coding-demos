package org.example.structural.decorator;

public class Main {
    public static void main(String[] args) {
        Fare uberGo = new UberGoFare();
        Fare uberXl = new UberXLFare();

        System.out.println("Uber Go Fare: " + uberGo.cost());
        System.out.println("Uber XL Fare:" + uberXl.cost());

        Fare surge = new SurgeFareDecorator(uberGo);
        System.out.println("Uber Go Fare: [Surge Pricing] " + surge.cost());

        Fare toll = new TollFareDecorator(uberXl);
        System.out.println("Uber XL Fare: [Tolls] " + toll.cost());

    }
}
