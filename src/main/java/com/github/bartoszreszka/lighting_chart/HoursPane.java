package com.github.bartoszreszka.lighting_chart;

import java.awt.*;

public class HoursPane extends APane {

    @Override
    protected void drawPane(Graphics g, Color backgroundColor, Color drawColor, Font font) {
        setColor(g, backgroundColor, drawColor, font);
//        FontMetrics metrics = getFontMetrics(font);
        FontMetrics metrics = getFontMetrics(new Font("Arial", Font.PLAIN, Chart.fontHeight));
        for (int i = 0; i <= 6; i++) {
            int hours = i * 4;
            String hour = String.format("%02d:00", hours);
//            g.drawString(hour, ((20 + (4 * i * Chart.hourWidthInPixels)) - (metrics.stringWidth(hour)/2)), 30);
            g.drawString(hour, ((20 + (4 * i * Chart.hourWidthInPixels)) - 5), 30);
        }
    }
}
