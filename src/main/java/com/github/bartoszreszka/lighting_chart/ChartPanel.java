package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.bartoszreszka.lighting_chart.Computations.*;

public class ChartPanel extends JPanel {

    private JPanel chartPanel;
    private Polygon dayPolygon;
    private Shape moonCircle;
    private int panelWidth,
                panelLength;

    public ChartPanel() {
        panelWidth = 1440;
        panelLength = month.lengthOfMonth * 30;
        setSize(panelWidth, panelLength);
        drawPolygon();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLUE);
        g.setColor(Color.WHITE);
        g.fillPolygon(dayPolygon);
    }

    private void drawPolygon() {
        dayPolygon = new Polygon();
        int i = 0;

        for (Day day : month.days) {
            if (occurs(day.sunTimes.getRise(), day)) {
                try {
                    dayPolygon.addPoint(day.sunTimes.getRise().getHour() * 60 + day.sunTimes.getRise().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(0, i);
                } finally {
                    i += 30;
                }
            } else {
                dayPolygon.addPoint(0, i);
                i += 30;
            }
        }

        ArrayList<Day> reversedDays = new ArrayList<>(month.days);
        Collections.reverse(reversedDays);

        for (Day day : reversedDays) {
            i -= 30;
            if (occurs(day.sunTimes.getRise(), day)) {
                try {
                    dayPolygon.addPoint(day.sunTimes.getSet().getHour() * 60 + day.sunTimes.getSet().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(panelWidth, i);
                }
            } else {
                dayPolygon.addPoint(0, i);
            }
        }
    }

    JPanel getChartPanel() {
        return chartPanel;
    }
}
