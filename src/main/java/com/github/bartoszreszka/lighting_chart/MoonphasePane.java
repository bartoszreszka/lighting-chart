package com.github.bartoszreszka.lighting_chart;

import org.shredzone.commons.suncalc.MoonPhase;

import java.awt.*;
import java.time.ZonedDateTime;

public class MoonphasePane extends APane {

    private Shape newMoon,
                  firstQuarter,
                  fullMoon,
                  lastQuarter;

    @Override
    protected void drawPane(Graphics g, Color backgroundColor, Color drawColor, Font font) {
        setColor(g, backgroundColor, drawColor, font);
        g.translate(0, -(Chart.moonSize/3));
        newMoon(g);
        firstQuarter(g);
        fullMoon(g);
        lastQuarter(g);
    }

    private void lastQuarter(Graphics g) {
        g.drawOval(
                0,
                Chart.dayHeightInPixels * getDayValueOf(MoonPhase.Phase.LAST_QUARTER, Computations.month),
                Chart.moonSize,
                Chart.moonSize);
        g.fillArc(
                0,
                Chart.dayHeightInPixels * getDayValueOf(MoonPhase.Phase.LAST_QUARTER, Computations.month),
                Chart.moonSize,
                Chart.moonSize,
                270,
                180);
    }

    private void fullMoon(Graphics g) {
        g.drawOval(
                0,
                Chart.dayHeightInPixels * getDayValueOf(MoonPhase.Phase.FULL_MOON, Computations.month),
                Chart.moonSize,
                Chart.moonSize);
    }

    private void firstQuarter(Graphics g) {
        g.fillArc(
                0,
                Chart.dayHeightInPixels * getDayValueOf(MoonPhase.Phase.FIRST_QUARTER, Computations.month),
                Chart.moonSize,
                Chart.moonSize,
                90,
                180);
        g.drawOval(
                0,
                Chart.dayHeightInPixels * getDayValueOf(MoonPhase.Phase.FIRST_QUARTER, Computations.month),
                Chart.moonSize,
                Chart.moonSize);
    }

    private void newMoon(Graphics g) {
        g.fillOval(
                0,
                Chart.dayHeightInPixels * getDayValueOf(MoonPhase.Phase.NEW_MOON, Computations.month),
                Chart.moonSize,
                Chart.moonSize);
    }


    private int getDayValueOf(MoonPhase.Phase moonPhase, Month month) {
        ZonedDateTime zdt = Computations.dayOfMoonPhase(moonPhase, Computations.month);
        if (zdt.getMonthValue() == Computations.month.value) {
            return zdt.getDayOfMonth() - 1;
        } else {
            return -1;
        }
    }
}
