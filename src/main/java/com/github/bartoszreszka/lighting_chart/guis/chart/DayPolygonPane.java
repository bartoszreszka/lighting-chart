package com.github.bartoszreszka.lighting_chart.guis.chart;

import com.github.bartoszreszka.lighting_chart.computations.Day;
import com.github.bartoszreszka.lighting_chart.commons.Phenomena;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.bartoszreszka.lighting_chart.guis.chart.Chart.*;
import static com.github.bartoszreszka.lighting_chart.computations.Computations.doesPhenomenonOccursOnGivenDay;
import static com.github.bartoszreszka.lighting_chart.computations.Computations.month;

public class DayPolygonPane extends APane {

    private final int panelWidth,
                      panelHeight;
    private Polygon dayPolygon;

    public DayPolygonPane() {
        panelWidth = 24 * hourWidthInPixels;
        panelHeight = month.lengthOfMonth * dayHeightInPixels;
        repaint();
    }

    @Override
    protected void drawPane(Graphics g) {
        g.translate(0, dayHeightInPixels/4);
        createDayPolygon();
        setColorsAndFont(g, nightColor, dayColor, null);
        g.fillPolygon(dayPolygon);
        drawMoonSymbols(g, Phenomena.MOONRISE, moonColorBright, textColor);
        drawMoonSymbols(g, Phenomena.MOONSET, moonColorDark, textColor);
    }

    private void createDayPolygon() {
        dayPolygon = new Polygon();
        int i = 0;

        for (Day day : month.days) {
            if (doesPhenomenonOccursOnGivenDay(day.sunTimes.getRise(), day)) {
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
            if (doesPhenomenonOccursOnGivenDay(day.sunTimes.getRise(), day)) {
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

    private void drawMoon(Graphics g, Color fillColor, Color drawColor, int x, int y) {
        g.setColor(drawColor);
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

    private void drawMoonSymbols(Graphics g, Phenomena phenomena, Color fillColor, Color drawColor) {
        int i = 0;
        for (Day day : month.days) {
            drawMoon(g,
                    fillColor,
                    drawColor,
                    hourWidthInPixels * getHourOf(phenomena, day)
                            + ((hourWidthInPixels / 60) * getMinutesOf(phenomena, day))
                            - (moonSize / 2),
                    i * dayHeightInPixels - (moonSize / 2));
            i++;
        }
    }
}
