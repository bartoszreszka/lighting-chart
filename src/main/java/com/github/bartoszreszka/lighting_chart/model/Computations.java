package com.github.bartoszreszka.lighting_chart.model;

import org.shredzone.commons.suncalc.MoonPhase;
import org.shredzone.commons.suncalc.MoonTimes;
import org.shredzone.commons.suncalc.SunTimes;

import java.awt.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;
import java.util.Locale;

public class Computations {

    private static Month month;
    private static Location location;

    private Computations() {
    }

    public static void execute() {
        calculateSunTimesInMonth();
        calculateMoonTimesInMonth();
    }

    public static String getReport() {
        return timesReport();
    }

    public static String locationName() {
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb);
        if (!(location.locName().isEmpty())) {
            f.format(location.locName());
        } else {
            char phi = 966,
                    lambda = 955,
                    deg = 176;
            f.format(Locale.US, "%3$c = %05.2f%5$c %4$c = %06.2f%5$c",
                    location.lat(), location.lng(), phi, lambda, deg);
        }
        return String.valueOf(sb);
    }

    public static boolean doesPhenomenonOccursOnGivenDay(ZonedDateTime phenomenonDateAndTime, Day day) {
        if (phenomenonDateAndTime == null) {
            return false;
        }
        return isSameDay(day.getZonedDateTime(), phenomenonDateAndTime);
    }

    public static ZonedDateTime dateAndTimeOfPhenomenonOnGivenDay(Phenomena phenomenon, Day day) {
        switch (phenomenon) {
            case SUNRISE:
                return doesPhenomenonOccursOnGivenDay(
                        day.getSunTimes().getRise(), day) ?
                        day.getSunTimes().getRise() : null;
            case SUNSET:
                return doesPhenomenonOccursOnGivenDay(
                        day.getSunTimes().getSet(), day) ?
                        day.getSunTimes().getSet() : null;
            case MOONRISE:
                return doesPhenomenonOccursOnGivenDay(
                        day.getMoonTimes().getRise(), day) ?
                        day.getMoonTimes().getRise() : null;
            case MOONSET:
                return doesPhenomenonOccursOnGivenDay(
                        day.getMoonTimes().getSet(), day) ?
                        day.getMoonTimes().getSet() : null;
            default:
                return null;
        }
    }

    public static ZonedDateTime dateAndTimeOfMoonPhaseInGivenMonth(MoonPhase.Phase moonPhase, Month month) {
        return MoonPhase.compute()
                .on(month.days.get(0)
                        .getZonedDateTime())
                .phase(moonPhase)
                .execute()
                .getTime();
    }

    public static Month getMonth() {
        return month;
    }

    public static Location getLocation() {
        return location;
    }

    public static void setMonth(Month month) {
        Computations.month = month;
    }

    public static void setLocation(Location location) {
        Computations.location = location;
    }

    private static void calculateSunTimesInMonth() {
        for (Day day : month.days) {
            day.setSunTimes(SunTimes.compute()
                    .on(day.getYear(), day.getMonth(), day.getDay())
                    .at(location.lat(), location.lng())
                    .execute());
        }
    }

    private static void calculateMoonTimesInMonth() {
        for (Day day : month.days) {
            day.setMoonTimes(MoonTimes.compute()
                    .on(day.getYear(), day.getMonth(), day.getDay())
                    .at(location.lat(), location.lng())
                    .execute());
        }
    }

    private static String timesReport() {
        StringBuilder sb = new StringBuilder(locationName() + "\n");
        Formatter f = new Formatter(sb);
        APane aPane = new APane() {
            @Override
            protected void drawPane(Graphics g) {
            }
        };
        for (Day day : month.days) {
            f.format(day + "\nWschód słońca    %02d:%02d | " +
                            "Wschód księżyca  %02d:%02d" +
                           "\nZachód słońca    %02d:%02d | " +
                            "Zachód księżyca  %02d:%02d\n"
                    , aPane.getHourOf(Phenomena.SUNRISE, day)
                    , aPane.getMinutesOf(Phenomena.SUNRISE, day)
                    , aPane.getHourOf(Phenomena.MOONRISE, day)
                    , aPane.getMinutesOf(Phenomena.MOONRISE, day)
                    , aPane.getHourOf(Phenomena.SUNSET, day)
                    , aPane.getMinutesOf(Phenomena.SUNSET, day)
                    , aPane.getHourOf(Phenomena.MOONSET, day)
                    , aPane.getMinutesOf(Phenomena.MOONSET, day)
            );
        }
        return String.valueOf(sb);
    }

    private static boolean isSameDay(ZonedDateTime zdt1, ZonedDateTime zdt2) {
        return zdt1.truncatedTo(ChronoUnit.DAYS).equals(zdt2.truncatedTo(ChronoUnit.DAYS));
    }
}