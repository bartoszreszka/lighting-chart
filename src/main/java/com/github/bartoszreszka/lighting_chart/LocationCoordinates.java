package com.github.bartoszreszka.lighting_chart;

public class LocationCoordinates implements Location {

    private final double lat,
                         lng;
    private final String locationName;

    public LocationCoordinates(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
        this.locationName = "";
    }

    public LocationCoordinates(double lat, double lng, String locationName) {
        this.lat = lat;
        this.lng = lng;
        this.locationName = locationName;
    }

    @Override
    public double lat() {
        return lat;
    }

    @Override
    public double lng() {
        return lng;
    }

    @Override
    public String locName() {
        return locationName;
    }
}