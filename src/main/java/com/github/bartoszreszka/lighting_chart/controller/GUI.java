package com.github.bartoszreszka.lighting_chart.controller;

import com.github.bartoszreszka.lighting_chart.Main;
import com.github.bartoszreszka.lighting_chart.model.*;
import com.github.bartoszreszka.lighting_chart.view.About;
import com.github.bartoszreszka.lighting_chart.view.Chart;
import org.shredzone.commons.suncalc.MoonPhase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.stream.Collectors;

public class GUI extends JFrame {
    private JPanel contentPane;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JComboBox<String> predefinedHarboursComboBox;
    private JTextField latDegField;
    private JTextField longDegField;
    private JTextField latMinutesField;
    private JTextField longMinutesField;
    private JTextField pointNameField;
    private JButton buttonOK;

    public GUI() {
        setTitle(Main.getProgTitle() + " " + Main.getVersion());
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        setIconImage(Main.getIcon().getImage());
        setUpMenu();

        predefinedHarboursComboBox.setModel(
                new javax.swing.DefaultComboBoxModel<>(
                        Arrays.stream(Locations.values())
                        .map(Locations::locName)
                        .toArray(String[]::new)
        ));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        setVisible(true);
    }

    private void setUpMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Pomoc");
        menu.setMnemonic(KeyEvent.VK_P);

        JMenuItem about = new JMenuItem("O programie");
        about.setMnemonic(KeyEvent.VK_O);
        about.addActionListener(e -> about());

        menu.add(about);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    private void onOK() {
        Computations.setMonth(parseMonthAndYearFromSpinners());
        Computations.setLocation(parseCoordsFromTextFields());
        new Chart();
        this.setVisible(false);
        printToStandardOutput();
    }

    private void about() {
        new About();
    }

    private Month parseMonthAndYearFromSpinners() {
        return new Month(getYearFromSpinner(), getMonthFromSpinner());
    }

    public Integer getYearFromSpinner() {
        return (Integer) spinner2.getValue();
    }

    public Integer getMonthFromSpinner() {
        return (Integer) spinner1.getValue();
    }

    private Location parseCoordsFromTextFields() {
        Location location;
        double  lat,
                lng;
        int     latDeg,
                lngDeg;
        double  latMinutes,
                lngMinutes;

        if (coordsAreProvided()) {
            if (!latMinutesField.getText().isEmpty() || !longMinutesField.getText().isEmpty()) {
                // Parse degrees and minutes given separately
                latDeg = Integer.parseInt(latDegField.getText());
                latMinutes = Double.parseDouble(latMinutesField.getText());
                lngDeg = Integer.parseInt(longDegField.getText());
                lngMinutes = Double.parseDouble(longMinutesField.getText());
                lat = latDeg + (latMinutes / 60);
                lng = lngDeg + (lngMinutes / 60);
            } else {
                // Parse degrees given in decimal format
                lat = Double.parseDouble(latDegField.getText().replaceAll("[,]", "."));
                lng = Double.parseDouble(longDegField.getText().replaceAll("[,]", "."));
            }
            if (!pointNameField.getText().isEmpty()) {
                location = new LocationCoordinates(lat, lng, pointNameField.getText());
            } else {
                location = new LocationCoordinates(lat, lng);
            }
        } else {
            // Get harbour selected from comboBox
            location = Arrays.stream(Locations.values())
                    .filter(locations -> locations.locName()
                            .equals(predefinedHarboursComboBox.getSelectedItem().toString()))
                    .collect(Collectors.toList())
                    .get(0);
        }
        return location;
    }

    private boolean coordsAreProvided() {
        return !latDegField.getText().isEmpty() && !longDegField.getText().isEmpty();
    }

    private void printToStandardOutput() {
        System.out.println(Computations
                .getReport());
        System.out.printf("Nów: %tc%n", Computations
                .dateAndTimeOfMoonPhaseInGivenMonth(MoonPhase.Phase.NEW_MOON, Computations.getMonth()));
        System.out.printf("Pierwsza kwadra: %tc%n", Computations
                .dateAndTimeOfMoonPhaseInGivenMonth(MoonPhase.Phase.FIRST_QUARTER, Computations.getMonth()));
        System.out.printf("Pełnia: %tc%n", Computations
                .dateAndTimeOfMoonPhaseInGivenMonth(MoonPhase.Phase.FULL_MOON, Computations.getMonth()));
        System.out.printf("Ostatnia kwadra: %tc%n", Computations
                .dateAndTimeOfMoonPhaseInGivenMonth(MoonPhase.Phase.LAST_QUARTER, Computations.getMonth()));
    }

    private void createUIComponents() {
        spinner1 = new JSpinner(new SpinnerNumberModel(
                        Calendar.getInstance().get(Calendar.MONTH) + 1,
                        1,
                        12,
                        1));
        spinner2 = new JSpinner(new SpinnerNumberModel(
                        Calendar.getInstance().get(Calendar.YEAR),
                        1900,
                        2200,
                        1));
    }
}
