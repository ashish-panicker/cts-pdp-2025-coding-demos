package org.example.structural.abstract_factory;

public class BookingService {

    private final RegionFactory regionFactory;

    public BookingService(RegionFactory regionFactory) {
        this.regionFactory = regionFactory;
    }

    public void bookRide(String type, double distance) {
        var ride = regionFactory.createRide(type);
        var pricing = regionFactory.createPricingStratergy();

        System.out.println("Ride: " + ride.type());
        System.out.println("Price: " + pricing.calculateFare(distance));
    }

    public static void main(String[] args) {

        BookingService indiaService = new BookingService(new IndiaRegionFactory());
        BookingService usService = new BookingService(new USRegionFactory());

        indiaService.bookRide("Auto", 6);
        usService.bookRide("XL", 35);

    }
}
