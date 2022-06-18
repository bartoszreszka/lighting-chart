package com.github.bartoszreszka.lighting_chart.view;

import com.github.bartoszreszka.lighting_chart.model.APane;
import com.github.bartoszreszka.lighting_chart.model.Day;
import com.github.bartoszreszka.lighting_chart.model.Phenomena;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.bartoszreszka.lighting_chart.model.Computations.*;
import static com.github.bartoszreszka.lighting_chart.view.Chart.*;

public class DayPolygonPane extends APane {

    private final int panelWidth,
                      panelHeight;
    private Polygon dayPolygon;

    public DayPolygonPane() {
        panelWidth = 24 * hourWidthInPixels;
        panelHeight = getMonth().lengthOfMonth * dayHeightInPixels;
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

        for (Day day : getMonth().days) {
            if (doesPhenomenonOccursOnGivenDay(day.getSunTimes().getRise(), day)) {
                try {
                    dayPolygon.addPoint(
                            day.getSunTimes().getRise().getHour() * hourWidthInPixels + day.getSunTimes().getRise().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(
                            0, i);
                } finally {
                    i += dayHeightInPixels;
                }
            } else {
                dayPolygon.addPoint(
                        0, i);
                i += dayHeightInPixels;
            }
        }

        ArrayList<Day> reversedDays = new ArrayList<>(getMonth().days);
        Collections.reverse(reversedDays);

        for (Day day : reversedDays) {
            i -= dayHeightInPixels;
            if (doesPhenomenonOccursOnGivenDay(day.getSunTimes().getRise(), day)) {
                try {
                    dayPolygon.addPoint(
                            day.getSunTimes().getSet().getHour() * hourWidthInPixels + day.getSunTimes().getSet().getMinute(), i);
                } catch (NullPointerException npe) {
                    dayPolygon.addPoint(
                            panelWidth, i);
                }
            } else {
                dayPolygon.addPoint(
                        0, i);
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
        for (Day day : getMonth().days) {
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
