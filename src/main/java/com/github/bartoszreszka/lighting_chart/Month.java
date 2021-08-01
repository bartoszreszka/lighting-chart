package com.github.bartoszreszka.lighting_chart;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Class representing a set of days - usually a month.
 */
class Month {
    ArrayList<Day> days = new ArrayList<>();

    public Month(int year, int month) {
        int length = YearMonth.of(year, month).lengthOfMonth();
        for (int i = 0; i < length; i++) {
            this.days.add(new Day(year, month, i + 1));
        }
    }

    // TODO: 01.08.2021 Constructor for a specific number of days - between explicitly specified dates.
}
