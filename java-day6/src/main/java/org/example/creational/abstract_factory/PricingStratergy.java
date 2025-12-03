package org.example.creational.abstract_factory;

public interface PricingStratergy {
    double calculateFare(double distance);
}

// US Pricing startergy
class USPricingStratergy implements PricingStratergy {
    @Override
    public double calculateFare(double distance) {
        return 100 + distance * 12; // Higher
    }
}

// India pricing statergy
class IndiaPricingStratergy implements PricingStratergy {
    @Override
    public double calculateFare(double distance) {
        return 45 + distance * 10;
    }
}
