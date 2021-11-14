package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.bartoszreszka.lighting_chart.Computations.*;

public class DayPolygonPane extends JPanel {

    private final int panelWidth,
                      panelHeight;
    private Polygon dayPolygon;

    public DayPolygonPane() {
        panelWidth = 24 * Chart.hourWidthInPixels;
        panelHeight = month.lengthOfMonth * Chart.dayHeightInPixels;
        setSize(panelWidth, panelHeight);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        createDayPolygon();
        drawDayPolygon(g, Chart.backgroundColor, Chart.dayColor);
    }

    private void createDayPolygon() {
        dayPolygon = new Polygon();
        int i = 0;

        for (Day day : month.days) {
            if (occurs(day.sunTimes.getRise(), day)) {
                try {
                    dayPolygon.addPoint(day.sunTimes.getRise().getHour() * Chart.hourWidthInPixels + day.sunTimes.getRise().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(0, i);
                } finally {
                    i += Chart.dayHeightInPixels;
                }
            } else {
                dayPolygon.addPoint(0, i);
                i += Chart.dayHeightInPixels;
            }
        }

        ArrayList<Day> reversedDays = new ArrayList<>(month.days);
        Collections.reverse(reversedDays);

        for (Day day : reversedDays) {
            i -= Chart.dayHeightInPixels;
            if (occurs(day.sunTimes.getRise(), day)) {
                try {
                    dayPolygon.addPoint(day.sunTimes.getSet().getHour() * Chart.hourWidthInPixels + day.sunTimes.getSet().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(panelWidth, i);
                }
            } else {
                dayPolygon.addPoint(0, i);
            }
        }
    }

    private void drawDayPolygon(Graphics g, Color backgroundColor, Color dayColor) {
        setBackground(backgroundColor);
        g.setColor(dayColor);
        g.fillPolygon(dayPolygon);
    }
}