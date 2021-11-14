package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;

public class RulerPane extends JPanel {

    private JPanel rulerPane;

    public RulerPane() {
        setSize(2000, 200);
        repaint();
    }

    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // This makes the text look better:
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        g2d.setFont(Chart.font);
        g2d.setColor(Chart.textColor);
        g2d.setBackground(Chart.textBackgroundColor);

        // TODO: 14.11.2021 Draw strings in bounding rectangles: https://stackoverflow.com/a/27740330/11030256
        g2d.drawString("sunrise", 0, Chart.fontHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
