package com.github.bartoszreszka.lighting_chart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Calendar;

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
    private JSpinner spinner2;

    public GUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Populates ComboBox with harbour names from Locations enum.
        predefinedHarboursComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                Arrays.stream(Locations.values()).map(Locations::locName).toArray(String[]::new)
        ));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        Computations.month = new Month((Integer)spinner2.getValue(), (Integer)spinner1.getValue());
        Computations.location = parseCoords();
        Computations.execute();
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

        // Parse degrees and minutes given separately:
        if (!latMinutesField.getText().isEmpty() || !longMinutesField.getText().isEmpty()) {
            latDeg = Integer.parseInt(latDegField.getText());
            latMinutes = Double.parseDouble(latMinutesField.getText());
            lngDeg = Integer.parseInt(longDegField.getText());
            lngMinutes = Double.parseDouble(longMinutesField.getText());
            lat = latDeg + (latMinutes / 60);
            lng = lngDeg + (lngMinutes / 60);
        // Parse degrees given in decimal format:
        } else {
            lat = Double.parseDouble(latDegField.getText());
            lng = Double.parseDouble(longDegField.getText());
        }
        if (!pointNameField.getText().isEmpty()) {
            location = new LocationCoordinates(lat, lng, pointNameField.getText());
        } else {
            location = new LocationCoordinates(lat, lng);
        }
        return location;
    }

    private void createUIComponents() {
        spinner1 = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.MONTH), 1, 12, 1));
        spinner2 = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1));
    }
}
