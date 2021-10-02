package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Chart extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonPrint;
    JPanel chartPanel;

    public Chart() {
        setTitle("Grafik Oświetlenia.");
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setResizable(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        setVisible(true);

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
//        loadTextPanel();
        loadChartPanel();
    }

    private void loadTextPanel() {
        Computations.execute();
        chartPanel.add(new TextPanel(Computations.print()).getTextPanel());
        contentPane.revalidate();
    }

    private void loadChartPanel() {
        Computations.execute();
        ChartPanel cp = new ChartPanel();
        setSize(cp.getSize());
        chartPanel.add(cp);
        setLocationRelativeTo(null);
        contentPane.revalidate();
    }

    private void onOK() {
        Main.gui.setVisible(true);
        dispose();
    }
}
