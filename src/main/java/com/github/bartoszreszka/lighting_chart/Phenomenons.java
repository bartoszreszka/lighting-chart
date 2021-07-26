package com.github.bartoszreszka.lighting_chart;

public enum Phenomenons {
    SUNRISE ("sunrise"),
    SUNSET ("sunset"),
    MOONRISE ("rise"),
    MOONSET ("set"),
    MOONFRACTION ("fraction"),
    MOONPHASE ("phase"),
    MOONANGLE ("angle");

    private final String phenomenon;

    Phenomenons (String phenomenon) {
        this.phenomenon = phenomenon;
    }

    String get() {
        return phenomenon;
    }
}
