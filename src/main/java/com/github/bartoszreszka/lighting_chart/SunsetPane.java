package com.github.bartoszreszka.lighting_chart;

import java.awt.*;
import java.util.Formatter;

import static com.github.bartoszreszka.lighting_chart.Computations.*;

public class SunsetPane extends APane {

    @Override
    protected void drawPane(Graphics g, Color backgroundColor, Color drawColor, Font font) {
        setColor(g, backgroundColor, drawColor, font);
        int i = 0;
        for (Day day : month.days) {
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
