package com.github.bartoszreszka.lighting_chart.model;

import org.shredzone.commons.suncalc.MoonTimes;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

/**
 * Stores {@link #sunTimes} and {@link #moonTimes} for a single day explicitly specified
 * by {@link #year}, {@link #month} and {@link #day}.
 * */
public class Day {

    private final int year,
            month,
            day;
    private final ZoneId zoneId = ZoneId.systemDefault();
    private SunTimes sunTimes;
    private MoonTimes moonTimes;

    public Day() {
        this.year = ZonedDateTime.now().getYear();
        this.month = ZonedDateTime.now().getMonthValue();
        this.day = ZonedDateTime.now().getDayOfMonth();
    }

    public Day(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public ZonedDateTime getZonedDateTime() {
        return ZonedDateTime.of(
                year, month, day,
                12, 0, 0, 0,
                zoneId);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public SunTimes getSunTimes() {
        return sunTimes;
    }

    public MoonTimes getMoonTimes() {
        return moonTimes;
    }

    public void setSunTimes(SunTimes sunTimes) {
        this.sunTimes = sunTimes;
    }

    public void setMoonTimes(MoonTimes moonTimes) {
        this.moonTimes = moonTimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Day)) return false;
        Day thatDayObject = (Day) o;
        int[] thisDay = new int[] {this.year, this.month, this.day};
        int[] thatDay = new int[] {thatDayObject.year, thatDayObject.month, thatDayObject.day};
        return Arrays.equals(thisDay, thatDay);
    }

    @Override
    public String toString() {
        return String.format("%02d.%02d." + year, day, month);
    }
}