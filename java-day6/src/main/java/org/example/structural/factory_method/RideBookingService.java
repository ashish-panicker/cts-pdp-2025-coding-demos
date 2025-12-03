package org.example.structural.factory_method;

public class RideBookingService {
    public static void main(String[] args) {
        RideBookingService bookingService
                = new RideBookingService();

        bookingService.bookRide(new UberGoFactory(), 12);
        bookingService.bookRide(new UberXLFactory(), 30);
        bookingService.bookRide(new UberAutoFactory(), 5);
    }

    public void bookRide(RideFactory factory, double distance) {
        Ride ride = factory.createRide();
        System.out.println("Vehicle: " + ride.vehicleType());
        System.out.println("Price: " + ride.calculateFare(distance));

    }
}
