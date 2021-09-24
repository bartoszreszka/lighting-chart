package com.github.bartoszreszka.lighting_chart;

/**
 * Program calculates and generates "lighting chart" for following phenomenons in a given month:
 * <ul>
 *     <li>sunrise (top edge of the sun appears on the horizon);</li>
 *     <li>sunset (sun disappears below the horizon);</li>
 *     <li>moonrise (top edge of the moon appears on the horizon);</li>
 *     <li>moonset (moon disappears below the horizon);</li>
 *     <li>and moon phase.</li>
 * </ul>
 * <br>
 * @see     <a href="https://shredzone.org/">commons-suncalc</a>;
 * @version 0.2.1
 * @author  Bartosz Reszka
 * */
public class Main {
    public static void main(String[] args) {
        new GUI();
    }
}