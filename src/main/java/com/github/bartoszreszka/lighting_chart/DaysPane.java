package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;

import static com.github.bartoszreszka.lighting_chart.Computations.month;

public class DaysPane extends JPanel {

    public DaysPane() {
        setVisible(true);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDaysPane(g, Chart.textBackgroundColor, Chart.textColor, Chart.font);
    }

    private void drawDaysPane(Graphics g, Color backgroundColor, Color fontColor, Font font) {
        setBackground(backgroundColor);
        g.setColor(fontColor);
        g.setFont(font);
        int i = 0;
        for (Day day : month.days) {
            g.drawString(String.valueOf(day.getZonedDateTime().getDayOfMonth()),
                    0,
                    i * Chart.dayHeightInPixels + (Chart.fontHeight / 2));
            i++;
        }
    }
}
