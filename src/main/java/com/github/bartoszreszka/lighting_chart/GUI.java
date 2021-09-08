package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JSpinner spinner1;
    private JComboBox predefinedHarboursComboBox;
    private JTextField latDegField;
    private JTextField longDegField;
    private JTextField latMinutesField;
    private JTextField longMinutesField;
    private JTextField pointNameField;

    public GUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        predefinedHarboursComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                Arrays.stream(Locations.values()).map(Locations::locName).toArray(String[]::new)
        ));
    }

    private void onOK() {
        Computations.location = parseCoords();
        dispose();
    }

    private Location parseCoords() {
        Location location;
        double  lat,
                lng;
        int     latDeg,
                lngDeg;
        double  latMinutes,
                lngMinutes;

        // Parsing degrees and minutes given separately:
        if (!latMinutesField.getText().isEmpty() || !longMinutesField.getText().isEmpty()) {
            latDeg = Integer.parseInt(latDegField.getText());
            latMinutes = Double.parseDouble(latMinutesField.getText());
            lngDeg = Integer.parseInt(longDegField.getText());
            lngMinutes = Double.parseDouble(longMinutesField.getText());
            lat = latDeg + (latMinutes / 60);
            lng = lngDeg + (lngMinutes / 60);
        // Parsing degrees given in decimal format:
        } else {
            lat = Double.parseDouble(latDegField.getText());
            lng = Double.parseDouble(longMinutesField.getText());
        }
        if (!pointNameField.getText().isEmpty()) {
            location = new LocationCoordinates(lat, lng, pointNameField.getText());
        } else {
            location = new LocationCoordinates(lat, lng);
        }
        return location;
    }
}
