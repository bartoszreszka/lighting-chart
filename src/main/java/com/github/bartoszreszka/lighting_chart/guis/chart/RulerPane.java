package com.github.bartoszreszka.lighting_chart.guis.chart;

import java.awt.*;

public class RulerPane extends APane {

    @Override
    protected void drawPane(Graphics g) {
        for (int i = 0; i <= 6; i++) {
            g.drawLine(Chart.hourWidthInPixels * i * 4, 10, Chart.hourWidthInPixels * i * 4, 15);
        }
        g.drawLine(0, 15, Chart.hourWidthInPixels * 24, 15);
    }
}
