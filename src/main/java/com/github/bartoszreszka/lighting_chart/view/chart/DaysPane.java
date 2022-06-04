package com.github.bartoszreszka.lighting_chart.view.chart;

import com.github.bartoszreszka.lighting_chart.model.APane;
import com.github.bartoszreszka.lighting_chart.model.Day;

import java.awt.*;

import static com.github.bartoszreszka.lighting_chart.controller.Computations.getMonth;

public class DaysPane extends APane {

    @Override
    protected void drawPane(Graphics g) {
        int i = 0;
        for (Day day : getMonth().days) {
            g.drawString(String.valueOf(day.getZonedDateTime().getDayOfMonth()),
                    0,
                    i * Chart.dayHeightInPixels + (Chart.fontHeight / 2));
            i++;
        }
    }
}
