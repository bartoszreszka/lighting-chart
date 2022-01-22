package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chart extends JDialog {
    static int dayHeightInPixels;
    static int hourWidthInPixels;
    static int fontHeight;
    static int moonSize;
    static Font font;
    static Color backgroundColor;
    static Color dayColor;
    static Color textBackgroundColor;
    static Color textColor;
    static Color moonColorBright;
    static Color moonColorDark;
    private JPanel contentPane;
    private JPanel titlePane;
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
        moonColorBright = Color.YELLOW;
        moonColorDark = Color.DARK_GRAY;
        moonSize = 20;
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

        sunrisePane.add(new SunrisePane());
        daysPaneLeft.add(new DaysPane());
        DayPolygonPane dpp = new DayPolygonPane();
        dayPolygonPane.add(dpp);
        daysPaneRight.add(new DaysPane());
        sunsetPane.add(new SunsetPane());
        moonrisePane.add(new MoonrisePane());
        moonsetPane.add(new MoonsetPane());
        moonphasePane.add(new MoonphasePane());
        // Fixed size in form file has been set: rulerPane 1440, daysPaneLeft 600.
//        setSize(dpp.getWidth(), dpp.getHeight() + 80);
        pack();
        setLocationRelativeTo(null);
        contentPane.revalidate();
    }
}
