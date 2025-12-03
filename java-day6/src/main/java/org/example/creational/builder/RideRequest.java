package org.example.creational.builder;

// Represents a ride requested by the user
// Pick up, drop locations, distance, ride type,
// Payment type, promo code, surge pricing, ETA
public class RideRequest {

    private final String riderId;
    private final String driverId;
    private final Location pickUp;
    private final Location drop;
    private final String eta;

    public RideRequest(Builder builder) {
        this.riderId = builder.riderId;
        this.driverId = builder.driverId;
        this.pickUp = builder.pickUp;
        this.drop = builder.drop;
        this.eta = builder.eta;
    }

    public static class Builder {

        private String riderId;
        private String driverId;
        private Location pickUp;
        private Location drop;
        private String eta;

        public Builder eta(String eta) {
            this.eta = eta;
            return this;
        }

        public Builder drop(Location drop) {
            this.drop = drop;
            return this;
        }

        public Builder pickUp(Location pickUp) {
            this.pickUp = pickUp;
            return this;
        }

        public Builder riderId(String riderId) {
            this.riderId = riderId;
            return this;
        }

        public Builder driverId(String driverId) {
            this.driverId = driverId;
            return this;
        }

        public RideRequest build() {
            return new RideRequest(this);
        }

    }

    // @Builder -> Lombok
    public static void main(String[] args) {
        new RideRequest
                .Builder()
                .riderId("")
                .driverId("")
                .pickUp(null)
                .drop(null)
                .eta("")
                .build();
    }

}

// RideRequest.Builder().driverId().riderId().pickup().drop().build();