package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;

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

    protected void setColor (Graphics g, Color backgroundColor, Color fontColor, Font font) {
        setBackground(backgroundColor);
        g.setColor(fontColor);
        g.setFont(font);
    }

    protected void drawPane(Graphics g, Color backgroundColor, Color fontColor, Font font) {}

}
