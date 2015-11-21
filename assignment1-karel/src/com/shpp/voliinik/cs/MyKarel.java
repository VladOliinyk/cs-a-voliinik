/**
 * Created by Vlad on 21.11.2015.
 */

package com.shpp.voliinik.cs;

import com.shpp.karel.KarelTheRobot;

public class MyKarel extends KarelTheRobot {

    public void run() throws Exception {
        doVictoryDance();
    }

    public void doVictoryDance() throws Exception{
        turnLeft();
        turnLeft();
        turnLeft();
        turnLeft();
    }
}
