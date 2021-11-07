package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.bartoszreszka.lighting_chart.Computations.*;

public class DayPolygonPane extends JPanel {

    private final int panelWidth,
                      panelHeight,
                      dayStepInPixels,
                      hourStepInPixels;
    private Polygon dayPolygon;


    public DayPolygonPane() {
        dayStepInPixels = 20;
        hourStepInPixels = 60;
        panelWidth = 24 * hourStepInPixels;
        panelHeight = month.lengthOfMonth * dayStepInPixels;
        setSize(panelWidth, panelHeight);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        createDayPolygon();
        drawDayPolygon(g, Color.BLUE, Color.WHITE);
    }

    private void createDayPolygon() {
        dayPolygon = new Polygon();
        int i = 0;

        for (Day day : month.days) {
            if (occurs(day.sunTimes.getRise(), day)) {
                try {
                    dayPolygon.addPoint(day.sunTimes.getRise().getHour() * hourStepInPixels + day.sunTimes.getRise().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(0, i);
                } finally {
                    i += dayStepInPixels;
                }
            } else {
                dayPolygon.addPoint(0, i);
                i += dayStepInPixels;
            }
        }

        ArrayList<Day> reversedDays = new ArrayList<>(month.days);
        Collections.reverse(reversedDays);

        for (Day day : reversedDays) {
            i -= dayStepInPixels;
            if (occurs(day.sunTimes.getRise(), day)) {
                try {
                    dayPolygon.addPoint(day.sunTimes.getSet().getHour() * hourStepInPixels + day.sunTimes.getSet().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(panelWidth, i);
                }
            } else {
                dayPolygon.addPoint(0, i);
            }
        }
    }

    private void drawDayPolygon(Graphics g, Color backgroundColor, Color dayColor) {
        setBackground(Color.BLUE);
        g.setColor(Color.WHITE);
        g.fillPolygon(dayPolygon);
    }

    private void drawHourRuler() {

    }
}
