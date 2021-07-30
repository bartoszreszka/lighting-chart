package com.github.bartoszreszka.lighting_chart;

import java.time.ZonedDateTime;
import java.util.Arrays;

public class Day {

    private int year;
    private int month;
    private int day;

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

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
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
        StringBuilder sb = new StringBuilder();
        sb.append(day).append(".").append(month).append(".").append(year);
        return sb.toString();
    }
}