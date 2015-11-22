package com.shpp.voliinik.cs;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {

    public void run() throws Exception {
        /* Precondition: Karel stay in south corner of map,
        *                He looks to the east.
        *  Result:       have only one beeper in middle of first(south) row
        */
        thatIsOneSmallStepForKarel();
    }

    /*
    * @return have one beeper in middle of first(south) row
    *         Karel stay on this beeper
    *         no more beepers in map
    *
    * */
    private void thatIsOneSmallStepForKarel() throws Exception {
        moveToAssignmentRow();
        fillRow();
        clearRow();
        markMidpoint();
    }

    /*
    * just step to the second row where karel tryes to find midpoint
    *
    * @result Karel stand on second row
    *         look to east (in direction of the row that Karel need to fill)
    * */
    private void moveToAssignmentRow() throws Exception {
        turnLeft();
        move();
        turnRight();
    }

    /*
    * fill row on which Karel stay in the direction where Karl looks.
    * @result:  if we have row N cells long,
     *          we have N-1 beepers in this row
     *          single beeper in each cell from the second cell
     *          [0][1][1]...[1]
     *
     *          Karel stay in last cell,
     *          look to east
    * */
    private void fillRow() throws Exception {
        while(frontIsClear()){
            move();
            putBeeper();
        }
    }

    /*
    * clear row, by picking beeper from each side
    *
    * @result:  row is clear
    *           beeper stand in center of row
    * */
    private void clearRow() throws Exception {
        pickBeeper();
        pickBeeperInOtherSide();
    }

    /*
    * mark the midpoint in first (south) row
    *
    * @result Karel put one beeper in:
    *                                   middle central cell if count of rows is odd [][][x][][]
    *                                   second (from start point) cell if count of rows is even [][][][x][][]
    * */
    private void markMidpoint() throws Exception {
        while(notFacingSouth()){
            turnLeft();
        }
        move();
        putBeeper();
    }

    /*
    * recursion
    *           pick beeper from another end of row on which Karel stays until put all beepers
    * @return   Karel stay in midpoint of row
    *           no beepers in this row
    * */
    private void pickBeeperInOtherSide() throws Exception {
        turnAround();
        move();
        while(beepersPresent()){
            move();
        }
        turnAround();
        move();
        turnAround();
        if(beepersPresent()) {
            pickBeeper();
            pickBeeperInOtherSide();
        }
    }


    /*
    * triple turn left
    * @result   Karel look right from his start position
    * */
    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /*
    * double turn left
    * @result   Karel look backward from his start position
    * */
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

}