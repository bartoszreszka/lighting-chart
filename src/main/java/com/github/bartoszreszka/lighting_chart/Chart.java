package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chart extends JDialog {
    static int dayHeightInPixels;
    static int hourWidthInPixels;
    static int fontHeight;
    static Font font;
    static Color backgroundColor;
    static Color dayColor;
    static Color textBackgroundColor;
    static Color textColor;
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

    static {
        dayHeightInPixels = 20;
        hourWidthInPixels = 60;
        fontHeight = 16;
//        font = new Font("Arial", Font.PLAIN, fontHeight);
        font = null;
        backgroundColor = Color.BLUE;
        dayColor = Color.WHITE;
        textBackgroundColor = null;
        textColor = Color.BLACK;
    }

    public Chart() {
        setTitle("Grafik Oświetlenia.");
        setContentPane(contentPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        setVisible(true);
        loadAllPanes();

        buttonOK.addActionListener(e -> onOK());

        buttonPrint.addActionListener(e -> onPrint());

        // Call onOK() when ESC button pressed
        contentPane.registerKeyboardAction(
                e -> onOK(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

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

        daysPaneLeft.add(new DaysPane());
        daysPaneRight.add(new DaysPane());

        DayPolygonPane dpp = new DayPolygonPane();
        dayPolygonPane.add(dpp);

        setSize(dpp.getWidth(), dpp.getHeight() + 80);
        setLocationRelativeTo(null);
        contentPane.revalidate();
    }

}
