package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;

import static com.github.bartoszreszka.lighting_chart.Computations.month;

public class DaysPane extends JPanel {

    private final int panelWidth,
                      panelHeight;
    private String days = "abece";

    public DaysPane() {
        panelWidth = 2 * Chart.hourStepInPixels;
        panelHeight = month.lengthOfMonth * Chart.dayStepInPixels;
        setSize(panelWidth, panelHeight);

        setVisible(true);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        createDaysPane();
        drawDaysPane(g, Chart.textBackgroundColor, Chart.textColor, Chart.font);
    }

    private void createDaysPane() {
    }

    private void drawDaysPane(Graphics g, Color backgroundColor, Color fontColor, Font font) {
        setBackground(backgroundColor);
        g.setColor(fontColor);
        g.drawString(days, 0, 0);
    }
}
