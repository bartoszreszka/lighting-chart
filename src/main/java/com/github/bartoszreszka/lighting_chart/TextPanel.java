package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;

public class TextPanel {

    private JPanel textPanel;
    private JTextArea textArea;

    public TextPanel(String s) {
        this.textArea.setText(s);
    }

    JPanel getTextPanel() {
        return textPanel;
    }
}
