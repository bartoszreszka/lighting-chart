package com.github.bartoszreszka.lighting_chart.view;

import com.github.bartoszreszka.lighting_chart.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class About extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel aboutLabel;
    private JLabel authorLabel;
    private JLabel adressLabel;
    private JLabel iconLabel;
    private JLabel downloadLabel;
    private JLabel eMail;

    public About() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setIconImage(Main.getIcon().getImage());
        setTitle(Main.getProgTitle() + " " + Main.getVersion() + " - o programie");
        iconLabel.setIcon(Main.getIcon());
        aboutLabel.setText("Program służy do obliczania i wykreślania \"grafiku oświetlenia\" dla predefiniowanej " +
                "lub wprowadzonej lokalizacji.");
        downloadLabel.setText("Pobierz najnowszą wersję programu:");
        adressLabel.setText("https://bartoszreszka.github.io/lighting-chart/");
        authorLabel.setText("\u00a9 Bartosz Reszka, 2022");
        adressLabel.setForeground(Color.BLUE.darker());
        adressLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adressLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop()
                            .browse(new URI("https://bartoszreszka.github.io/lighting-chart/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                } finally {
                    dispose();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                adressLabel.setText("<html><a href=''>https://bartoszreszka.github.io/lighting-chart/</a></html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                adressLabel.setText("https://bartoszreszka.github.io/lighting-chart/");
            }
        });
        eMail.setText("br.reszka@gmail.com");
        eMail.setForeground(Color.BLUE.darker());
        eMail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String subject = Main.getProgTitle() + " " + Main.getVersion();
                    String uri = "mailto:br.reszka@gmail.com?subject=" + subject;
                    Desktop.getDesktop()
                            .browse(new URI(uri.replaceAll(" ", "%20")));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                } finally {
                    dispose();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                eMail.setText("<html><a href=''>br.reszka@gmail.com</a></html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                eMail.setText("br.reszka@gmail.com");
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void onOK() {
        dispose();
    }
}
