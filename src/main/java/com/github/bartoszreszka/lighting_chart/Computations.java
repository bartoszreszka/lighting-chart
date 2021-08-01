package com.github.bartoszreszka.lighting_chart;

import org.shredzone.commons.suncalc.MoonTimes;
import org.shredzone.commons.suncalc.SunTimes;

public class Computations {

    static Month month;
    static Location location;

    public static void execute() {
        sunTimesInMonth();
        moonTimesInMonth();
        printLocation();
        printTimes();
    }

    private static void sunTimesInMonth() {
        for (Day day : month.days) {
            day.sunTimes = SunTimes.compute()
                    .on(day.year, day.month, day.day)
                    .at(location.lat(), location.lng())
                    .execute();
        }
    }

    private static void moonTimesInMonth() {
        for (Day day : month.days) {
            day.moonTimes = MoonTimes.compute()
                    .on(day.year, day.month, day.day)
                    .at(location.lat(), location.lng())
                    .execute();
        }
    }

    private static void printLocation() {
        System.out.println("***   " + location.locName() + "   ***");
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

