package com.github.bartoszreszka.lighting_chart;

public enum Phenomenons {
    SUNRISE ("sunrise"),
    SUNSET ("sunset"),
    SUNTIMES ("suntimes"),
    MOONRISE ("moonrise"),
    MOONSET ("moonset"),
    MOONTIMES ("moontimes"),
    MOONPHASE ("moonphase"), // Date and time of new moon, full moon and half moons.
    ALL ("all");

    private final String phenomenon;

    Phenomenons (String phenomenon) {
        this.phenomenon = phenomenon;
    }

    String get() {
        return phenomenon;
    }
}
