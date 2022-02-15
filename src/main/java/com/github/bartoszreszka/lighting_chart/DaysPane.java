package com.github.bartoszreszka.lighting_chart;

import java.awt.*;

import static com.github.bartoszreszka.lighting_chart.Computations.month;

public class DaysPane extends APane {

    @Override
    protected void drawPane(Graphics g) {
        int i = 0;
        for (Day day : month.days) {
            g.drawString(String.valueOf(day.getZonedDateTime().getDayOfMonth()),
                    0,
                    i * Chart.dayHeightInPixels + (Chart.fontHeight / 2));
            i++;
        }
    }
}
