package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Chart extends JDialog {
    // <-- Change chart appearance here:
    static final int dayHeightInPixels = 20;
    static final int hourWidthInPixels = 60;
    static int fontHeight = 16;
    static Font font = new Font("Arial", Font.BOLD, fontHeight);
    static Color backgroundColor = Color.BLUE;
    static Color dayColor = Color.WHITE;
    static Color textBackgroundColor = Color.WHITE;
    static Color textColor = Color.BLACK;
    // -->

    private JPanel contentPane;
        private JPanel rulerPane;
        private JPanel chartPane;
            private JPanel dayPolygonPane;
            private JPanel daysPaneLeft;
            private JPanel daysPaneRight;
            private JPanel sunrisePane;
            private JPanel sunsetPane;
            private JPanel moonrisePane;
            private JPanel moonsetPane;
            private JPanel moonphasePane;
        private JPanel buttonPane;
            private JButton buttonOK;
            private JButton buttonPrint;

    public Chart() {
        setTitle("Grafik Oświetlenia.");
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setSize(600, 400);
        setResizable(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        setVisible(true);
        loadAllPanes();

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPrint();
            }
        });

        // Call onOK() when ESC button pressed
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onPrint() {
        loadAllPanes();
    }

    private void onOK() {
        Main.gui.setVisible(true);
        dispose();
    }

    private void loadAllPanes() {
        Computations.execute();

        rulerPane.add(new RulerPane());

        DayPolygonPane dpp = new DayPolygonPane();
        dayPolygonPane.add(dpp);

        setSize(dpp.getWidth(), dpp.getHeight() + buttonPane.getHeight());
        setLocationRelativeTo(null);
        contentPane.revalidate();
    }

}
