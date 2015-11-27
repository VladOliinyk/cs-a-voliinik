package com.shpp.voliinik.cs;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {

    public void run() throws Exception {
        /* Precondition: Karel stay in eastwest corner of his house,
        *                He looks to the east.
        *  Result:       all of pillars completed (filled)
        */
        fillAllPillars();
    }

    /*
    * fill all pillars in world
    * @return: all pillars are filled
    * */
    private void fillAllPillars() throws Exception {
        while (frontIsClear()) {
            fillThePillar();
           // if (frontIsClear()){
                moveToNextPillar();
           // }
        }
        fillThePillar();
    }

    /*
    * fill one pillar (which located to the left of Karel)
    * @return Karel stay in start of pillar
     *        look to east
    * */
    private void fillThePillar() throws Exception {
        putBeeperIfNeeded();
        turnLeft();
        moveToWall();
        turnAround();
        while (frontIsClear()){
            putBeeperIfNeeded();
            move();
        }
        turnLeft();
    }

    /*
    * move to next pillar
    * @result: Karel stay in start of pillar
    *          pillar located to the left of Karel
    * */
    private void moveToNextPillar() throws Exception {
            move();
            move();
            move();
            move();
    }

    /*
    * put beeper in empty cell
    * */
    private void putBeeperIfNeeded() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }

    /*
    * move forward up to wall
    * */
    private void moveToWall() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

    /*
    * turn around
    * */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}