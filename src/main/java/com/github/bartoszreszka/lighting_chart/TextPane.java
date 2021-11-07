package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;

public class TextPane {

    private JPanel textPanel;
    private JTextArea textArea;

    public TextPane(String s) {
        this.textArea.setText(s);
    }

    JPanel getTextPanel() {
        return textPanel;
    }
}
