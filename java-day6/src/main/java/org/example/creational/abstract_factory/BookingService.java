package org.example.creational.abstract_factory;

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

    // Explain a scenario where Abstract Factory adds more complication without
    // adding more flexibility
    // PaymentSystem with UPIPayment, CardPayment, and WalletPayment.
    // Each family produces a Validator, Notifier, SettlementHandler
    // FactoryClasses: CardPaymentFactory, UPIPaymentFactory, WalletPaymentFactory
    // New requirement: UPIPayment + CardPayment
    // Abstract Factory Pattern promotes inheritance but not composition
    // Alternative patterns -> Builder + Stratergy + DI
}
