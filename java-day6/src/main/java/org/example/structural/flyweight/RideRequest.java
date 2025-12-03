package org.example.structural.flyweight;

public class RideRequest {

    private final RideType type;
    private final String pickUp;
    private final String drop;

    public RideRequest(String type, String pickUp, String drop) {
        this.type = RideTypeFlyweight.getRideType(type);
        this.pickUp = pickUp;
        this.drop = drop;
    }

    public void print() {
        System.out.println(
                type.getName() + " : from " +
                        pickUp + " to " +
                        drop
        );
    }


    public static void main(String[] args) {

        RideRequest req1 = new RideRequest("GO", "A","C");
        RideRequest req2 = new RideRequest("GO", "A","C");
        RideRequest req3 = new RideRequest("GO", "A","C");

        req1.print();
        req2.print();
        req3.print();

    }
}
