package com.github.bartoszreszka.lighting_chart.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel aboutLabel;
    private JLabel authorLabel;
    private JLabel adressLabel;

    public About() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        ImageIcon icon = new ImageIcon("eclipse.png");
        setIconImage(icon.getImage());

        aboutLabel.setText("Program służy do obliczania i kreślenia \"grafiku oświetlenia\" dla wybranej " +
                "lub wprowadzonej lokalizacji.");
        authorLabel.setText("Bartosz Reszka, 2022");
        adressLabel.setText("https://www.bartoszreszka.github.com/lighting-chart/");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onOK() {
        dispose();
    }
}
