package com.shpp.voliinik.cs;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {

    public void run() throws Exception {
        /* Precondition: Karel stay in northwest corner of his house,
        *                He looks to the east.
        *  Result:       Karel stay in northwest corner of his house,
        *                He looks to the east.
        */
        collectNewspaper();
    }

    /*
    *  Collect newspaper which lies on the doorstep(threshold)
    *  @return stand in start position with newspaper(beeper) in bag
    */
    private void collectNewspaper() throws Exception {
        comeToDoor();
        goOutside();
        pickNewspaper();
        goBackInside();
        comeBack();
    }

    /*
    * Come to the door from start position (northwest corner, look to east)
    * @return stand in front the door
    */
    private void comeToDoor() throws Exception {
        moveToWall();
        turnRight();
        move();
        turnLeft();
    }

    /*
    * Pass through the door
    * @return stand on the doorstep
    *         door is behind Karel
    */
    private void goOutside() throws Exception {
        move();
        move();
    }

    /*
    * Check for newspaper & pick it if it is (lol :D)
    */
    private void pickNewspaper() throws Exception {
        if (beepersPresent()) {
            pickBeeper();
            pickNewspaper(); // there we have recursive call because maybe the postman brought us a few newspapers
        }
    }

    /*
    * Come back inside house through door
    * @return stand in house,
    *         door is behind Karel
    */
    private void goBackInside() throws Exception {
        turnAround();
        move();
        move();
    }

    /*
    * Come back to start position
    */
    private void comeBack() throws Exception {
        moveToWall();
        turnRight();
        moveToWall();
        turnRight();
    }

    /*
    * Move forward before reaching the wall
    */
    private void moveToWall() throws Exception {
        while(frontIsClear()){
            move();
        }
    }

    /*
    * Turn right
    */
    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /*
    * Turn around
    */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}
