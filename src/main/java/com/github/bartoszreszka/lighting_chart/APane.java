package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;

import static com.github.bartoszreszka.lighting_chart.Computations.getDateTimeOf;

abstract class APane extends JPanel {

    public APane() {
        setVisible(true);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPane(g, Chart.textBackgroundColor, Chart.textColor, Chart.font);
    }

    // TODO: 22.01.2022 Create methods drawPane and setColor with less parameters e.g. w/o font. 
    
    protected void setColor (Graphics g, Color backgroundColor, Color drawColor, Font font) {
        setBackground(backgroundColor);
        g.setColor(drawColor);
        g.setFont(font);
    }

    protected void drawPane(Graphics g, Color backgroundColor, Color drawColor, Font font) {}

    protected int getHourOf (Phenomena phenomena, Day day) {
        int hour;
        try {
            hour = getDateTimeOf(phenomena, day).getHour();
        } catch (NullPointerException npe) {
            hour = -1;
        }
        return hour;
    }

    protected int getMinutesOf (Phenomena phenomena, Day day) {
        int minutes;
        try {
            minutes = getDateTimeOf(phenomena, day).getMinute();
        } catch (NullPointerException npe) {
            minutes = -1;
        }
        return minutes;
    }

}
