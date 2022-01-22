package com.github.bartoszreszka.lighting_chart;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Class representing a set of {@link Day}s - usually a month.
 */
class Month {
    int lengthOfMonth,
        value;
    ArrayList<Day> days = new ArrayList<>();

    public Month(int year, int month) {
        value = month;
        lengthOfMonth = YearMonth.of(year, month).lengthOfMonth();
        for (int i = 0; i < lengthOfMonth; i++) {
            this.days.add(new Day(year, month, i + 1));
        }
    }
}
