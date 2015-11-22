package com.shpp.voliinik.cs;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot{

    public void run() throws Exception {
        chessIsLife(); // (c) Bobby	Fischer's quote, an American chess Grandmaster
    }                  // https://en.wikipedia.org/wiki/Bobby_Fischer

    /*
    * Precondition: Karel stay in southwest corner of map,
    *               look to east
    *
    * Result:   chessboard is filled
    *
    * p.s.  row - set of cells from west to east (or vice versa)
    *       column - set of cells from north to south (or vice versa)
    * */
    private void chessIsLife() throws Exception {
        fillChessboard();
    }

    /*
    * fill the chessboard
    *
    * @return   chessboard is filed
    *           Karel stay in southwest corner of map,
    *           look to south
    * */
    private void fillChessboard() throws Exception {
        putFirstBeeper();
        fillRow();
        fillAllColumns();
    }

    /*
    * put first beeper in first (main) row
    * */
    private void putFirstBeeper() throws Exception {
        putBeeper();
    }

    /*
    * fill first (main) row
    *
    * @result   row filed
    *           Karel stands at the end of the row
    *           Karel look to east
    * */
    private void fillRow() throws Exception {
        while(frontIsClear()){
            move();
            if (frontIsClear()){
                move();
                putBeeper();
            }
        }
    }

    /*
    *   fill all columns in chessboard
    *   remind: Karel look to east, but row is behind Karel
    *
    *   @return all columns filed
    * */
    private void fillAllColumns() throws Exception {
        turnAround();
        while(frontIsClear()){
            fillColumn();
            stepToNextColumn();
        }
        fillColumn();
    }

    /*
    *   fill only one column
    *   here Karel look to west (in direction of his way)
    *   Karel will fill column on which stands (from right of it)
    *
    * @return   Karel look to south
    *           column is filled
    * */
    private void fillColumn() throws Exception {
        turnRight();
        if(frontIsClear()){
            if (beepersPresent()){
                fillRow();
            } else {
                move();
                putBeeper();
                fillRow();
            }
            turnAround();
            moveToWall();
        }
    }

    /*
    *   move to nest column (& rightly be in a position to fill column)
    *   remind: Karel look to south,
    *           next column to the left rom Karel
    *
    *   @return Karel look to west
    *           Karel stands at the main row
    *           Karel stands at the start of column which he need to fill
    * */
    private void stepToNextColumn() throws Exception {
        turnRight();
        if(frontIsClear()){
            move();
        }
    }

    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }
    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
    private void moveToWall() throws Exception {
        while(frontIsClear()){
            move();
        }
    }
}
