package com.github.bartoszreszka.lighting_chart;

public interface Location {
    double lat();
    double lng();
    double[] coords();
    String locName();
}
