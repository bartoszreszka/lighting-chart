package com.github.bartoszreszka.lighting_chart.view;

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
        ImageIcon icon = new ImageIcon("eclipse.png");
        setIconImage(icon.getImage());
        setTitle("O programie");
        iconLabel.setIcon(icon);
        aboutLabel.setText("Program służy do obliczania i wykreślania \"grafiku oświetlenia\" dla predefiniowanej " +
                "lub wprowadzonej lokalizacji.");
        downloadLabel.setText("Pobierz najnowszą wersję programu:");
        adressLabel.setText("https://github.com/bartoszreszka/lighting-chart/releases/latest/");
        authorLabel.setText("\u00a9 Bartosz Reszka, 2022");
        adressLabel.setForeground(Color.BLUE.darker());
        adressLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adressLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop()
                            .browse(new URI("https://github.com/bartoszreszka/lighting-chart/releases/latest/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                } finally {
                    dispose();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                adressLabel.setText("<html><a href=''>https://github.com/bartoszreszka/lighting-chart/releases/latest/</a></html>");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                adressLabel.setText("https://github.com/bartoszreszka/lighting-chart/releases/latest/");
            }
        });
        eMail.setText("br.reszka@gmail.com");
        eMail.setForeground(Color.BLUE.darker());
        eMail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop()
                            .browse(new URI("mailto:br.reszka@gmail.com?subject=Grafik%20oświetlenia"));
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
