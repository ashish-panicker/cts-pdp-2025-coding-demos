package org.example.creational.abstract_factory;

public interface Ride {
    String type();
}

// US Rides
class UberXL implements  Ride {
    @Override
    public String type() {
        return "Uber SUV";
    }
}

class UberBlack implements  Ride {
    @Override
    public String type() {
        return "Uber Luxury";
    }
}

// India Rides
class UberAuto implements  Ride {
    @Override
    public String type() {
        return "Uber Auto";
    }
}

class UberGo implements  Ride {
    @Override
    public String type() {
        return "Uber Hatchback";
    }
}