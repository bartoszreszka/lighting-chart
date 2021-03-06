package com.github.bartoszreszka.lighting_chart.view;

import com.github.bartoszreszka.lighting_chart.model.APane;
import com.github.bartoszreszka.lighting_chart.model.Day;
import com.github.bartoszreszka.lighting_chart.model.Phenomena;

import java.awt.*;
import java.util.Formatter;

import static com.github.bartoszreszka.lighting_chart.model.Computations.*;

public class SunrisePane extends APane {

    @Override
    protected void drawPane(Graphics g) {
        int i = 0;
        for (Day day : getMonth().days) {
            StringBuilder sb = new StringBuilder();
            Formatter f = new Formatter(sb);
            f.format("%02d:%02d",
                    getHourOf(Phenomena.SUNRISE, day),
                    getMinutesOf(Phenomena.SUNRISE, day));
            g.drawString(String.valueOf(f),
                    0,
                    i * Chart.dayHeightInPixels + (Chart.fontHeight / 2) + 1);
            i++;
            f.close();
        }
    }
}
