package com.github.bartoszreszka.lighting_chart;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.bartoszreszka.lighting_chart.Chart.*;
import static com.github.bartoszreszka.lighting_chart.Computations.month;
import static com.github.bartoszreszka.lighting_chart.Computations.occurs;

public class DayPolygonPane extends APane {

    private final int panelWidth,
                      panelHeight;
    private Polygon dayPolygon;

    public DayPolygonPane() {
        panelWidth = 24 * hourWidthInPixels;
        panelHeight = month.lengthOfMonth * dayHeightInPixels;
//        setSize(panelWidth, panelHeight);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        createDayPolygon();
        drawDayPolygon(g, nightColor, dayColor);
    }

    private void createDayPolygon() {
        dayPolygon = new Polygon();
        int i = 0;

        for (Day day : month.days) {
            if (occurs(day.sunTimes.getRise(), day)) {
                try {
                    dayPolygon.addPoint(day.sunTimes.getRise().getHour() * hourWidthInPixels + day.sunTimes.getRise().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(0, i);
                } finally {
                    i += dayHeightInPixels;
                }
            } else {
                dayPolygon.addPoint(0, i);
                i += dayHeightInPixels;
            }
        }

        ArrayList<Day> reversedDays = new ArrayList<>(month.days);
        Collections.reverse(reversedDays);

        for (Day day : reversedDays) {
            i -= dayHeightInPixels;
            if (occurs(day.sunTimes.getRise(), day)) {
                try {
                    dayPolygon.addPoint(day.sunTimes.getSet().getHour() * hourWidthInPixels + day.sunTimes.getSet().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(panelWidth, i);
                }
            } else {
                dayPolygon.addPoint(0, i);
            }
        }
    }

    private void drawMoon(Graphics g, Color fillColor, int x, int y) {
        g.setColor(textColor);
        g.drawOval(x,
                y,
                moonSize,
                moonSize);
        g.setColor(fillColor);
        g.fillOval(x,
                y,
                moonSize,
                moonSize);
    }

    private void drawMoonRisesAndMoonSets(Graphics g) {
        int i = 0;
        for (Day day : month.days) {
            drawMoon(g,
                    moonColorBright,
                    hourWidthInPixels * getHourOf(Phenomena.MOONRISE, day) + ((hourWidthInPixels / 60) * getMinutesOf(Phenomena.MOONRISE, day)),
                    i * dayHeightInPixels - (moonSize / 2));
            i++;
        }
        i = 0;
        for (Day day : month.days) {
            drawMoon(g,
                    moonColorDark,
                    hourWidthInPixels * getHourOf(Phenomena.MOONSET, day) + ((hourWidthInPixels / 60) * getMinutesOf(Phenomena.MOONSET, day)),
                    i * dayHeightInPixels - (moonSize / 2));
            i++;
        }
    }

    private void drawDayPolygon(Graphics g, Color backgroundColor, Color dayColor) {
        setBackground(backgroundColor);
        g.translate(0, dayHeightInPixels/4);
        g.setColor(dayColor);
        g.fillPolygon(dayPolygon);
        drawMoonRisesAndMoonSets(g);
    }
}
