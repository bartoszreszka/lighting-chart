package com.github.bartoszreszka.lighting_chart;

import static com.github.bartoszreszka.lighting_chart.Computations.*;
import static com.github.bartoszreszka.lighting_chart.Locations.*;

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
 * @version 1.1.0-alpha
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

        location = PW_GDYNIA;
        month = new Month(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        execute();

    }
}
