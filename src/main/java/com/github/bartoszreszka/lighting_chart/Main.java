package com.github.bartoszreszka.lighting_chart;

import static com.github.bartoszreszka.lighting_chart.Computations.*;

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

        /*
        * +1. Set location
        * +2. Set date
        * 3. Make calculations
        * 4. Aggregate results
        * 5. Repeat pts 2-4 as needed
        * 6. Print results in an approachable way.
        * */

        /* Set location and date manually - just for testing */
        location = new LocationCoordinates(54.5521d, 18.4555d);
//        month = new Month(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        month = new Month(2021, 8);
        execute();

        /* Create and display graphic user interface */
        new GUI().setVisible(true);
    }
}