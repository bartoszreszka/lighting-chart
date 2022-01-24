package com.github.bartoszreszka.lighting_chart;

import java.awt.*;

public class RulerPane extends APane {

    @Override
    protected void drawPane(Graphics g, Color backgroundColor, Color drawColor, Font font) {
        setColor(g, backgroundColor, drawColor, font);
        for (int i = 0; i <= 6; i++) {
            g.drawLine(Chart.hourWidthInPixels * i * 4, 10, Chart.hourWidthInPixels * i * 4, 15);
        }
        g.drawLine(0, 15, Chart.hourWidthInPixels * 24, 15);
    }
}
