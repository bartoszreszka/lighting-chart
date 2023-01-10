package com.github.bartoszreszka.lighting_chart;

import com.github.bartoszreszka.lighting_chart.controller.GUI;

import javax.swing.*;

/**
 * Program calculates and generates "lighting chart" for following phenomena in a given month:
 * <ul>
 *     <li>sunrise (top edge of the sun appears on the horizon);</li>
 *     <li>sunset (sun disappears below the horizon);</li>
 *     <li>moonrise (top edge of the moon appears on the horizon);</li>
 *     <li>moonset (moon disappears below the horizon);</li>
 *     <li>and moon phase.</li>
 * </ul>
 * <br>
 * @see     <a href="https://shredzone.org/">commons-suncalc</a>;
 * @version 1.1.1
 * @author  Bartosz Reszka
 * */
public class Main {

    public static GUI gui;
    private static final String progTitle = "Grafik Oświetlenia";
    private static final String version = "v1.1.1";
    private static final ImageIcon icon = new ImageIcon(Main.class.getResource("/eclipse.png"));
    // Uncomment below line for development in IntelliJ:
//        private static final ImageIcon icon = new ImageIcon("eclipse.png");

    public static void main(String[] args){
        gui = new GUI();
    }

    public static String getProgTitle() {
        return progTitle;
    }

    public static String getVersion() {
        return version;
    }

    public static ImageIcon getIcon() {
        return icon;
    }
}