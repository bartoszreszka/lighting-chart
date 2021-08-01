package com.github.bartoszreszka.lighting_chart;

public enum Locations implements Location {
    PW_GDYNIA (54.54d, 18.55d, "Port wojenny Gdynia."),
    PW_SWINO (53.90d, 14.25d, "Port wojenny Świnoujście.");

    private final double lat,
                         lng;
    private final String locationName;

    Locations(double lat, double lng, String locationName) {
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