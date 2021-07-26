package com.github.bartoszreszka.lighting_chart;

import org.shredzone.commons.suncalc.MoonTimes;
import org.shredzone.commons.suncalc.SunTimes;

public class Computations {

    // TODO: 26.07.2021 At the moment does not compute ALL phenomenons.
    public static void computeAll(Location location, Day day) {
        System.out.println(location.locName() + "\n" +
                SunTimes.compute()
                        .on(day.getYear(), day.getMonth(), day.getDay())
                        .at(location.lat(), location.lng())
                        .execute()
                        .getSet()
        );
        System.out.println(
                MoonTimes.compute()
                        .on(day.getYear(), day.getMonth(), day.getDay())
                        .at(location.lat(), location.lng())
                        .execute()
                        .getSet()
        );
    }
}