package com.github.bartoszreszka.lighting_chart.view;

import com.github.bartoszreszka.lighting_chart.model.Computations;
import com.github.bartoszreszka.lighting_chart.Main;
import com.github.bartoszreszka.lighting_chart.controller.Printer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Locale;

public class Chart extends JFrame {
    public static Font font;
    public static Color backgroundColor;
    public static Color textColor;
    static int dayHeightInPixels;
    static int hourWidthInPixels;
    static int fontHeight;
    static int titleFontHeight;
    static int moonSize;
    static Font titleFont;
    static Color nightColor;
    static Color dayColor;
    static Color textBackgroundColor;
    static Color moonColorBright;
    static Color moonColorDark;
    private JPanel contentPane;
    private JPanel titlePane;
    private JPanel rulerPane;
    private JPanel hoursPane;
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
    private JLabel titleLabel;

    static {
        dayHeightInPixels = 20;
        hourWidthInPixels = 40;
        fontHeight = 16;
        titleFontHeight = 24;
        font = null;
        titleFont = new Font("Arial", Font.BOLD, titleFontHeight);
        backgroundColor = Color.WHITE;
        nightColor = Color.BLUE;
        dayColor = Color.WHITE;
        textBackgroundColor = null;
        textColor = Color.BLACK;
        moonColorBright = Color.YELLOW;
        moonColorDark = Color.DARK_GRAY;
        moonSize = 18;
    }

    public Chart() {
        setTitle("Grafik Oświetlenia");
        setContentPane(contentPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        ImageIcon icon = new ImageIcon("eclipse.png");
        setIconImage(icon.getImage());
        setVisible(true);
        loadAllPanes();

        buttonOK.addActionListener(e -> onOK());
        buttonPrint.addActionListener(e -> onPrint());
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
        new Printer(contentPane);
    }

    private void onOK() {
        Main.gui.setVisible(true);
        dispose();
    }

    private void exit() {
        Main.gui.dispose();
        dispose();
    }

    private void setTitleLabel() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 15); // Prevents unintended behaviour.
        c.set(Calendar.MONTH, Main.gui.getMonthFromSpinner() - 1);
        String titleString = String.format("Grafik oświetlenia %s dla %s %d roku",
                Computations.locationName(),
                c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
                Main.gui.getYearFromSpinner());
        titleLabel.setFont(titleFont);
        titleLabel.setText(titleString);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
        rulerPane.add(new RulerPane());
        hoursPane.add(new HoursPane());
        titlePane.setBackground(backgroundColor);
        chartPane.setBackground(backgroundColor);
        buttonPane.setBackground(backgroundColor);
        setTitleLabel();
        setUpMenu();
        setSize(dpp.getWidth(), dpp.getHeight() + 80); // Fixed size in form file has been set: rulerPane 961, daysPaneLeft 610.
        pack();
        setLocationRelativeTo(null);
        contentPane.revalidate();
    }

    private void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Plik");
        menu.setMnemonic(KeyEvent.VK_P);

        JMenuItem printChart = new JMenuItem("Drukuj");
        printChart.setMnemonic(KeyEvent.VK_D);
        printChart.addActionListener(e -> onPrint());

        JMenuItem exit = new JMenuItem("Wyjdź");
        exit.setMnemonic(KeyEvent.VK_W);
        exit.addActionListener(e -> exit());

        menu.add(printChart);
        menu.add(exit);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }
}
