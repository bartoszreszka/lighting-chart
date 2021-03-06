package com.github.bartoszreszka.lighting_chart.model;

import com.github.bartoszreszka.lighting_chart.view.Chart;

import javax.swing.*;
import java.awt.*;

import static com.github.bartoszreszka.lighting_chart.model.Computations.dateAndTimeOfPhenomenonOnGivenDay;

public abstract class APane extends JPanel {

    public APane() {
        setVisible(true);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Sets default colors for convenience; if needed, invoke method again in child's class drawPane().
        setColorsAndFont(g, Chart.backgroundColor, Chart.textColor, Chart.font);
        drawPane(g);
    }

    protected abstract void drawPane(Graphics g);

    protected void setColorsAndFont(Graphics g, Color backgroundColor, Color drawColor, Font font) {
        setBackground(backgroundColor);
        g.setColor(drawColor);
        g.setFont(font);
    }

    public int getHourOf(Phenomena phenomena, Day day) {
        int hour;
        try {
            hour = dateAndTimeOfPhenomenonOnGivenDay(phenomena, day).getHour();
        } catch (NullPointerException npe) {
            hour = -1;
        }
        return hour;
    }

    public int getMinutesOf(Phenomena phenomena, Day day) {
        int minutes;
        try {
            minutes = dateAndTimeOfPhenomenonOnGivenDay(phenomena, day).getMinute();
        } catch (NullPointerException npe) {
            minutes = -1;
        }
        return minutes;
    }

}
