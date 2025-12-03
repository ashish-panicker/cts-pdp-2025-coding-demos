package org.example.structural.adapter;

// Adapter helps to integrate two incompatible classes
public class GoogleMapsAdapter implements NavigationService {

    private final GoogleMapsService maps = new GoogleMapsService();

    @Override
    public Route getRoute(Location from, Location to) {
        GooglePath path = maps.findPath(
          new Point(from.lat, from.lng),
          new Point(to.lat, to.lng)
        );
        return null;
    }
}
