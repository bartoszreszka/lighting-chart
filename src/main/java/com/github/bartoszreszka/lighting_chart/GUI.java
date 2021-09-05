package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JSpinner spinner1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;

    public GUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        comboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(
                Arrays.stream(Locations.values()).map(Locations::locName).toArray(String[]::new)
        ));
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
