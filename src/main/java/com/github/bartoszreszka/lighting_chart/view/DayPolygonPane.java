package com.github.bartoszreszka.lighting_chart.view;

import com.github.bartoszreszka.lighting_chart.model.APane;
import com.github.bartoszreszka.lighting_chart.model.Day;
import com.github.bartoszreszka.lighting_chart.model.Phenomena;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.bartoszreszka.lighting_chart.model.Computations.doesPhenomenonOccursOnGivenDay;
import static com.github.bartoszreszka.lighting_chart.model.Computations.getMonth;
import static com.github.bartoszreszka.lighting_chart.view.Chart.*;

public class DayPolygonPane extends APane {

    public DayPolygonPane() {
        repaint();
    }

    @Override
    protected void drawPane(Graphics g) {
        // TODO: 03.07.2022 Check for memory leak as drawPane() method is executed over and over again.
        g.translate(0, dayHeightInPixels / 4);
        Polygon dayPolygon = createDayPolygon();
        setColorsAndFont(g, nightColor, dayColor, null);
        g.fillPolygon(dayPolygon);
        dayPolygon.translate(-(hourWidthInPixels * 24), 0);
        g.fillPolygon(dayPolygon);
        drawMoonSymbols(g, Phenomena.MOONRISE, moonColorBright, textColor);
        drawMoonSymbols(g, Phenomena.MOONSET, moonColorDark, textColor);
    }

    private Polygon createDayPolygon() {
        Polygon tempDayPolygon = new Polygon();
        int i = 0;
        int sunrise = 0;
        int sunset;
        int previousSunrise;
        int dayPolygonPaneWidth = 24 * hourWidthInPixels;
        boolean sunrisePassingMidnight = false;

        previousSunrise = getMonth().days.get(0).getSunTimes().getRise().getHour() * hourWidthInPixels
                + ((int) (getMonth().days.get(0).getSunTimes().getRise().getMinute() * 0.0166d * hourWidthInPixels));

        for (Day day : getMonth().days) {
            if (doesPhenomenonOccursOnGivenDay(day.getSunTimes().getRise(), day)) {
                try {
                    sunrise = day.getSunTimes().getRise().getHour() * hourWidthInPixels
                            + ((int) (day.getSunTimes().getRise().getMinute() * 0.0166d * hourWidthInPixels));
                    if (previousSunrise < sunrise) {
                        if (Math.abs(previousSunrise - sunrise) > ((previousSunrise + dayPolygonPaneWidth) - sunrise)) {
                            tempDayPolygon.addPoint(dayPolygonPaneWidth - sunrise, i);
                            sunrisePassingMidnight = true;
                        } else {
                            tempDayPolygon.addPoint(sunrise, i);
                        }
                    } else if (previousSunrise > sunrise) {
                        if (Math.abs(previousSunrise - sunrise) > ((sunrise + dayPolygonPaneWidth) - previousSunrise)) {
                            tempDayPolygon.addPoint(dayPolygonPaneWidth + sunrise, i);
                            sunrisePassingMidnight = true;
                        } else {
                            tempDayPolygon.addPoint(sunrise, i);
                        }
                    } else {
                        tempDayPolygon.addPoint(sunrise, i);
                    }
                } catch (NullPointerException npe) {
                } finally {
                    i += dayHeightInPixels;
                }
            } else {
                i += dayHeightInPixels;
            }
        }

        ArrayList<Day> reversedDays = new ArrayList<>(getMonth().days);
        Collections.reverse(reversedDays);

        for (Day day : reversedDays) {
            i -= dayHeightInPixels;
            if (doesPhenomenonOccursOnGivenDay(day.getSunTimes().getSet(), day)) {
                try {
                    sunset = day.getSunTimes().getSet().getHour() * hourWidthInPixels
                            + ((int) (day.getSunTimes().getSet().getMinute() * 0.0166d * hourWidthInPixels));
                    if (sunrisePassingMidnight || (sunset < sunrise)) {
                        tempDayPolygon.addPoint(sunset + dayPolygonPaneWidth, i);
                    } else {
                        tempDayPolygon.addPoint(sunset, i);
                    }
                } catch (NullPointerException npe) {
                }
            }
        }
        return tempDayPolygon;
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
                    (int) (hourWidthInPixels * getHourOf(phenomena, day)
                            + ((0.0166d * hourWidthInPixels) * getMinutesOf(phenomena, day))
                            - (moonSize / 2)),
                    i * dayHeightInPixels - (moonSize / 2));
            i++;
        }
    }
}
