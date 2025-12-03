package org.example.structural.adapter;

class Location {
    public double lng;
    public double lat;
}
class Route {}

public interface NavigationService {
    Route getRoute(Location from, Location to);
}
