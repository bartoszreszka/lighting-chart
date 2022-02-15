package com.github.bartoszreszka.lighting_chart.guis.chart;

import java.awt.*;

public class HoursPane extends APane {

    @Override
    protected void drawPane(Graphics g) {
        for (int i = 0; i <= 6; i++) {
            int hours = i * 4;
            String hour = String.format("%02d:00", hours);
            g.drawString(hour, ((20 + (4 * i * Chart.hourWidthInPixels)) - 5), 30);
        }
    }
}
