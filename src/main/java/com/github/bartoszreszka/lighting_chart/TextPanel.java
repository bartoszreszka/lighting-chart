package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;

public class TextPanel extends JDialog {

    private JPanel chartPanel;
    private JTextArea textArea;

    public TextPanel(String s) {
        this.textArea.setText(s);
    }

    JPanel getPanel() {
        return chartPanel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
