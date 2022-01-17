package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;

import static com.github.bartoszreszka.lighting_chart.Computations.month;

public class DaysPane extends JPanel {

    private final int panelWidth,
                      panelHeight;
    private String days = "abece";

    public DaysPane() {
        panelWidth = 2 * Chart.hourWidthInPixels;
        panelHeight = month.lengthOfMonth * Chart.dayHeightInPixels;
        setSize(panelWidth, panelHeight);

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
        int i = 0;
        for (Day day : month.days) {
            g.drawString(String.valueOf(day.getZonedDateTime().getDayOfMonth()), 0, i * Chart.dayHeightInPixels);
            i++;
        }
    }
}
