package com.github.bartoszreszka.lighting_chart.commons;

/**
 * Geographic coordinates of a point on the Earth's surface.
 * {@link #locName()} may return an empty {@link String}.
 * */
public interface Location {
    double lat();
    double lng();
    String locName();
}
