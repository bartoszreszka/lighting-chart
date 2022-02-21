package com.github.bartoszreszka.lighting_chart.view.chart;

import com.github.bartoszreszka.lighting_chart.model.APane;
import com.github.bartoszreszka.lighting_chart.model.Day;
import com.github.bartoszreszka.lighting_chart.model.Phenomena;

import java.awt.*;
import java.util.Formatter;

import static com.github.bartoszreszka.lighting_chart.controller.Computations.*;

public class SunsetPane extends APane {

    @Override
    protected void drawPane(Graphics g) {
        int i = 0;
        for (Day day : getMonth().days) {
            StringBuilder sb = new StringBuilder();
            Formatter f = new Formatter(sb);
            f.format("%02d:%02d",
                    getHourOf(Phenomena.SUNSET, day),
                    getMinutesOf(Phenomena.SUNSET, day));
            g.drawString(String.valueOf(f),
                    0,
                    i * Chart.dayHeightInPixels + (Chart.fontHeight / 2));
            i++;
            f.close();
        }
    }
}
