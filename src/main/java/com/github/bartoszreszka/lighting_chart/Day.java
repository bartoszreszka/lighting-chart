package com.github.bartoszreszka.lighting_chart;

import java.time.ZonedDateTime;

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
}
