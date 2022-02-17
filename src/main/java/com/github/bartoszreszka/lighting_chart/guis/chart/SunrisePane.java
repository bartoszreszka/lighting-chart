package com.github.bartoszreszka.lighting_chart.guis.chart;

import com.github.bartoszreszka.lighting_chart.commons.APane;
import com.github.bartoszreszka.lighting_chart.computations.Day;
import com.github.bartoszreszka.lighting_chart.commons.Phenomena;

import java.awt.*;
import java.util.Formatter;

import static com.github.bartoszreszka.lighting_chart.computations.Computations.*;

public class SunrisePane extends APane {

    @Override
    protected void drawPane(Graphics g) {
        int i = 0;
        for (Day day : month.days) {
            StringBuilder sb = new StringBuilder();
            Formatter f = new Formatter(sb);
            f.format("%02d:%02d",
                    getHourOf(Phenomena.SUNRISE, day),
                    getMinutesOf(Phenomena.SUNRISE, day));
            g.drawString(String.valueOf(f),
                    0,
                    i * Chart.dayHeightInPixels + (Chart.fontHeight / 2));
            i++;
            f.close();
        }
    }
}
