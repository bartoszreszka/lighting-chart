package com.github.bartoszreszka.lighting_chart;

import org.shredzone.commons.suncalc.MoonTimes;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.ZonedDateTime;
import java.util.Arrays;

public class Day {

    final int year, month, day;
    SunTimes sunTimes;
    MoonTimes moonTimes;

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