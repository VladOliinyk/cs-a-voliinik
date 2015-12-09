package com.shpp.voliinik.cs;

import acm.graphics.GPoint;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part4 extends WindowProgram {

    /**
     * Global brick parameters:
     */
    public static final int BRICK_WIDTH = 100;
    public static final int BRICK_HEIGHT = 30;
    public static final int BRICKS_IN_BASE = 7;

    public static final boolean BRICK_FILLED = false; // true or false
    public static final Color BRICK_COLOR = new Color(0, 0, 0);
    public static final Color BRICK_BORDER_COLOR = new Color(0, 0, 0);

    /**
     * Global data.
     * Automatically calculated.
     */
    public static final int PYRAMID_WIDTH = BRICK_WIDTH * BRICKS_IN_BASE;
    public static final int PYRAMID_HEIGHT = BRICK_HEIGHT * BRICKS_IN_BASE + BRICK_HEIGHT;

    /**
     * Application width & height.
     * Automatically calculated.
     * Just for good view. This variables not used in Assignment3Part4
     */
    public static final int APPLICATION_WIDTH = PYRAMID_WIDTH + BRICK_WIDTH;
    public static final int APPLICATION_HEIGHT = PYRAMID_HEIGHT + BRICK_HEIGHT;

    public void run() {
        buildPyramid();
    }

    /**
     * Main method.
     * Calling draw row method in cycle for each row.
     */
    private void buildPyramid() {
        for (int rowNumber = 1; rowNumber <= BRICKS_IN_BASE; rowNumber++) {  // 1st row have BRICKS_IN_BASE bricks
            drawRow(rowNumber);
        }
    }

    /**
     * Draw row method.
     * Drawing one row.
     * Calling draw brick method in cycle for each brick in the row.
     *
     * @param rowNumber Number of row which drawing.
     */
    private void drawRow(int rowNumber) {
        for (int brickNumber = 0; brickNumber < rowNumber; brickNumber++) {
            GPoint brickCoordinates = getBrickCoordinates(rowNumber, brickNumber);
            double x = brickCoordinates.getX();
            double y = brickCoordinates.getY();

            drawBrick(x, y);
        }
    }

    /**
     * Getting brick coords.
     *
     * @param rowNumber   Row number which have brick for which calculating coordinates.
     * @param brickNumber Brick number for which calculating coordinates.
     * @return GPoint element with the same coordinates as brick.
     */
    private GPoint getBrickCoordinates(int rowNumber, int brickNumber) {
        double rowWidth = BRICK_WIDTH * rowNumber;

        double x = (getWidth() / 2) - ((rowWidth) / 2) + brickNumber * BRICK_WIDTH;
        double y = getHeight() + ((rowNumber - BRICKS_IN_BASE - 1) * BRICK_HEIGHT);

        return new GPoint(x, y);
    }

    /**
     * Draw brick method.
     * Drawing brick.
     *
     * @param x X coordinate of brick which draw.
     * @param y Y coordinate of brick which draw
     */
    private void drawBrick(double x, double y) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        if (BRICK_FILLED) {
            brick.setFilled(true);
            brick.setFillColor(BRICK_COLOR);
        }
        brick.setColor(BRICK_BORDER_COLOR);
        add(brick);
    }


}
