package com.github.bartoszreszka.lighting_chart;

import org.shredzone.commons.suncalc.SunTimes;

public class Computations {

    static Location location;
    static Month month;

    public static void computeAll(Location location, Day day) {
        System.out.println(location.locName() + "\n" +
                SunTimes.compute()
                        .on(day.getYear(), day.getMonth(), day.getDay())
                        .at(location.lat(), location.lng())
                        .execute()
                        .getSet()
        );
    }
}

