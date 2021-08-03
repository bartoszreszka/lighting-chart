package com.github.bartoszreszka.lighting_chart;

import org.shredzone.commons.suncalc.MoonTimes;
import org.shredzone.commons.suncalc.SunTimes;

import java.util.Locale;

public class Computations {

    static Month month;
    static Location location;

    public static void execute() {
        calculateSunTimesInMonth();
        calculateMoonTimesInMonth();
        printLocationName();
        printTimes();
    }

    private static void calculateSunTimesInMonth() {
        for (Day day : month.days) {
            day.sunTimes = SunTimes.compute()
                    .on(day.year, day.month, day.day)
                    .at(location.lat(), location.lng())
                    .execute();
        }
    }

    private static void calculateMoonTimesInMonth() {
        for (Day day : month.days) {
            day.moonTimes = MoonTimes.compute()
                    .on(day.year, day.month, day.day)
                    .at(location.lat(), location.lng())
                    .execute();
        }
    }

    private static void printLocationName() {
        if (!(location.locName().isEmpty())) {
            System.out.println("***   " + location.locName() + "   ***");
        } else {
            char phi = 966,
                lambda = 955,
                deg = 176;
            System.out.printf(Locale.US, "***   %3$c = %05.2f%5$c %4$c = %06.2f%5$c   ***\n", location.lat(), location.lng(), phi, lambda, deg);
        }
    }

    private static void printTimes() {
        for (Day day : month.days) {
            System.out.printf(day
                    + "\nSunrise %02d:%02d | Moonrise %02d:%02d\nSunset  %02d:%02d | Moonset  %02d:%02d\n"
                    ,day.sunTimes.getRise().getHour()
                    ,day.sunTimes.getRise().getMinute()
                    ,day.moonTimes.getRise().getHour()
                    ,day.moonTimes.getRise().getMinute()
                    ,day.sunTimes.getSet().getHour()
                    ,day.sunTimes.getSet().getMinute()
                    ,day.moonTimes.getSet().getHour()
                    ,day.moonTimes.getSet().getMinute()
                    );
        }
    }
}

