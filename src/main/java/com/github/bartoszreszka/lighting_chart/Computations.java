package com.github.bartoszreszka.lighting_chart;

import org.shredzone.commons.suncalc.MoonTimes;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;
import java.util.Locale;

public class Computations {

    static Month month;
    static Location location;

    static void execute() {
        calculateSunTimesInMonth();
        calculateMoonTimesInMonth();
    }
    
    static String printReport() {
        return printTimesReport();
    }

    static boolean occurs(ZonedDateTime phenomenonOccurrenceZonedDateTime, Day day) {
        if (phenomenonOccurrenceZonedDateTime == null) {
            return false;
        }
        return isSameDay(day.getZonedDateTime(), phenomenonOccurrenceZonedDateTime);
    }

    static int getHourOf (Phenomena phenomenon, Day day) {
        switch (phenomenon) {
            case SUNRISE:
                return occurs(day.sunTimes.getRise(), day) ? day.sunTimes.getRise().getHour() : -1;
            case SUNSET:
                return occurs(day.sunTimes.getSet(), day) ? day.sunTimes.getSet().getHour() : -1;
            case MOONRISE:
                return occurs(day.moonTimes.getRise(), day) ? day.moonTimes.getRise().getHour() : -1;
            case MOONSET:
                return occurs(day.moonTimes.getSet(), day) ? day.moonTimes.getSet().getHour() : -1;
            default:
                return -1;
        }
    }

    static int getMinutesOf (Phenomena phenomenon, Day day) {
        switch (phenomenon) {
            case SUNRISE:
                return occurs(day.sunTimes.getRise(), day) ? day.sunTimes.getRise().getMinute() : -1;
            case SUNSET:
                return occurs(day.sunTimes.getSet(), day) ? day.sunTimes.getSet().getMinute() : -1;
            case MOONRISE:
                return occurs(day.moonTimes.getRise(), day) ? day.moonTimes.getRise().getMinute() : -1;
            case MOONSET:
                return occurs(day.moonTimes.getSet(), day) ? day.moonTimes.getSet().getMinute() : -1;
            default:
                return -1;
        }
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

    private static String printLocationName() {
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb);
        if (!(location.locName().isEmpty())) {
            f.format("***   " + location.locName() + "   ***");
        } else {
            char phi = 966,
                lambda = 955,
                deg = 176;
            f.format(Locale.US, "***   %3$c = %05.2f%5$c %4$c = %06.2f%5$c   ***\n", location.lat(), location.lng(), phi, lambda, deg);
        }
        return String.valueOf(sb);
    }

    private static String printTimesReport() {
        StringBuilder sb = new StringBuilder(printLocationName() + "\n");
        Formatter f = new Formatter(sb);
        for (Day day : month.days) {
            f.format(day + "\nSunrise %02d:%02d | Moonrise %02d:%02d\nSunset  %02d:%02d | Moonset  %02d:%02d\n"
                    ,getHourOf(Phenomena.SUNRISE, day)
                    ,getMinutesOf(Phenomena.SUNRISE, day)
                    ,getHourOf(Phenomena.MOONRISE, day)
                    ,getMinutesOf(Phenomena.MOONRISE, day)
                    ,getHourOf(Phenomena.SUNSET, day)
                    ,getMinutesOf(Phenomena.SUNSET, day)
                    ,getHourOf(Phenomena.MOONSET, day)
                    ,getMinutesOf(Phenomena.MOONSET, day)
            );
        }
        return String.valueOf(sb);
    }

    private static boolean isSameDay(ZonedDateTime zdt1, ZonedDateTime zdt2) {
        return zdt1.truncatedTo(ChronoUnit.DAYS).equals(zdt2.truncatedTo(ChronoUnit.DAYS));
    }
}