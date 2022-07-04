package com.github.bartoszreszka.lighting_chart;

import com.github.bartoszreszka.lighting_chart.controller.GUI;

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
 * @version 1.0.1
 * @author  Bartosz Reszka
 * */
public class Main {

    public static GUI gui;

    public static void main(String[] args) {
        gui = new GUI();
    }
}