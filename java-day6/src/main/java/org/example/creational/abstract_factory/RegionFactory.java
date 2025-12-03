package org.example.creational.abstract_factory;

public interface RegionFactory {
    Ride createRide(String category);
    PricingStratergy createPricingStratergy();
}

// US Region Factory
class USRegionFactory implements RegionFactory {
    @Override
    public Ride createRide(String category) {
        return switch (category) {
            case "Black" -> new UberBlack();
            case "XL" -> new UberXL();
            default -> throw new IllegalStateException("Unexpected value: " + category);
        };
    }

    @Override
    public PricingStratergy createPricingStratergy() {
        return new USPricingStratergy();
    }
}

// India Region Factory
class IndiaRegionFactory implements RegionFactory {
    @Override
    public Ride createRide(String category) {
        return switch (category) {
            case "GO" -> new UberGo();
            case "Auto" -> new UberAuto();
            default -> throw new IllegalStateException("Unexpected value: " + category);
        };
    }

    @Override
    public PricingStratergy createPricingStratergy() {
        return new IndiaPricingStratergy();
    }
}
