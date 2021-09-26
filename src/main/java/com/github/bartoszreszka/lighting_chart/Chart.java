package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chart extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonPrint;
    private JTextArea textArea1;

    public Chart() {
        setTitle("Grafik Oświetlenia.");
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);

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

        setVisible(true);
    }

    private void onPrint() {
        Computations.execute();
        fillTextArea();
    }

    private void onOK() {
        dispose();
    }

    private void fillTextArea() {
        textArea1.setText(Computations.print());
        pack();
        setLocationRelativeTo(null);
    }
}
