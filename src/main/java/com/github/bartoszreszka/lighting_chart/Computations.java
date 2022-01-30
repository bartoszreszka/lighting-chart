package com.github.bartoszreszka.lighting_chart;

import org.shredzone.commons.suncalc.MoonPhase;
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
    
    static String report() {
        return timesReport();
    }

    static boolean occurs(ZonedDateTime phenomenonOccurrenceZonedDateTime, Day day) {
        if (phenomenonOccurrenceZonedDateTime == null) {
            return false;
        }
        return isSameDay(day.getZonedDateTime(), phenomenonOccurrenceZonedDateTime);
    }

    // Replaced with APane.getHourOf(...)
//    static int getHourOf (Phenomena phenomenon, Day day) {
//        switch (phenomenon) {
//            case SUNRISE:
//                return occurs(day.sunTimes.getRise(), day) ? day.sunTimes.getRise().getHour() : -1;
//            case SUNSET:
//                return occurs(day.sunTimes.getSet(), day) ? day.sunTimes.getSet().getHour() : -1;
//            case MOONRISE:
//                return occurs(day.moonTimes.getRise(), day) ? day.moonTimes.getRise().getHour() : -1;
//            case MOONSET:
//                return occurs(day.moonTimes.getSet(), day) ? day.moonTimes.getSet().getHour() : -1;
//            default:
//                return -1;
//        }
//    }

    // Replaced with APane.getMinutesOf(...)
//    static int getMinutesOf (Phenomena phenomenon, Day day) {
//        switch (phenomenon) {
//            case SUNRISE:
//                return occurs(day.sunTimes.getRise(), day) ? day.sunTimes.getRise().getMinute() : -1;
//            case SUNSET:
//                return occurs(day.sunTimes.getSet(), day) ? day.sunTimes.getSet().getMinute() : -1;
//            case MOONRISE:
//                return occurs(day.moonTimes.getRise(), day) ? day.moonTimes.getRise().getMinute() : -1;
//            case MOONSET:
//                return occurs(day.moonTimes.getSet(), day) ? day.moonTimes.getSet().getMinute() : -1;
//            default:
//                return -1;
//        }
//    }

    static ZonedDateTime getDateTimeOf (Phenomena phenomenon, Day day) {
        switch (phenomenon) {
            case SUNRISE:
                return occurs(day.sunTimes.getRise(), day) ? day.sunTimes.getRise() : null;
            case SUNSET:
                return occurs(day.sunTimes.getSet(), day) ? day.sunTimes.getSet() : null;
            case MOONRISE:
                return occurs(day.moonTimes.getRise(), day) ? day.moonTimes.getRise() : null;
            case MOONSET:
                return occurs(day.moonTimes.getSet(), day) ? day.moonTimes.getSet() : null;
            default:
                return null;
        }
    }

    static ZonedDateTime dayOfMoonPhase (MoonPhase.Phase moonPhase, Month month) {
        return MoonPhase.compute()
                .on(month.days.get(0)
                .getZonedDateTime())
                .phase(moonPhase)
                .execute()
                .getTime();
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

    static String locationName() {
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb);
        if (!(location.locName().isEmpty())) {
            f.format(location.locName());
        } else {
            char phi = 966,
                lambda = 955,
                deg = 176;
            f.format(Locale.US, "%3$c = %05.2f%5$c %4$c = %06.2f%5$c", location.lat(), location.lng(), phi, lambda, deg);
        }
        return String.valueOf(sb);
    }

    private static String timesReport() {
        StringBuilder sb = new StringBuilder(locationName() + "\n");
        Formatter f = new Formatter(sb);
        APane aPane = new APane() {};
        for (Day day : month.days) {
            f.format(day + "\nSunrise %02d:%02d | Moonrise %02d:%02d\nSunset  %02d:%02d | Moonset  %02d:%02d\n"
                    ,aPane.getHourOf(Phenomena.SUNRISE, day)
                    ,aPane.getMinutesOf(Phenomena.SUNRISE, day)
                    ,aPane.getHourOf(Phenomena.MOONRISE, day)
                    ,aPane.getMinutesOf(Phenomena.MOONRISE, day)
                    ,aPane.getHourOf(Phenomena.SUNSET, day)
                    ,aPane.getMinutesOf(Phenomena.SUNSET, day)
                    ,aPane.getHourOf(Phenomena.MOONSET, day)
                    ,aPane.getMinutesOf(Phenomena.MOONSET, day)
            );
        }
        return String.valueOf(sb);
    }

    private static boolean isSameDay(ZonedDateTime zdt1, ZonedDateTime zdt2) {
        return zdt1.truncatedTo(ChronoUnit.DAYS).equals(zdt2.truncatedTo(ChronoUnit.DAYS));
    }
}