package com.shpp.voliinik.cs;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;


    /* Calculating the application width and height. */
    public static final int APPLICATION_WIDTH = (int) (NUM_COLS*(BOX_SIZE+BOX_SPACING))+100;
    public static final int APPLICATION_HEIGHT = (int) (NUM_ROWS*(BOX_SIZE+BOX_SPACING))+50;

    /**
     * Calling drawMatrix method.
     */
    public void run() {
        drawMatrix();
    }

    /**
     * Calling a drawBox method.
     * (with the number of columns and rows in the parameters)
     */
    private void drawMatrix() {
        for ( int i = 0; i < NUM_ROWS; i++ ) {
            for (int j = 0; j < NUM_COLS; j++) {
                drawBox(i, j);
            }
        }
    }

    /**
     * Сalculating the coordinates of the box.
     * Drawing box.
     * @param rowNumber The number of rows in the grid.
     * @param colNumber The number of columns in the grid.
     */
    private void drawBox(int rowNumber, int colNumber) {

        double xCoord = getXcoord(colNumber);
        double yCoord = getYcoord(rowNumber);

        GRect box = new GRect(xCoord, yCoord, BOX_SIZE, BOX_SIZE);
        box.setFilled(true);
        box.setColor(Color.BLACK);
        box.setFillColor(Color.BLACK);
        add(box);
    }

    /**
     *
     * @param rowNumber The sequence number of box in the row.
     * @return Y coordinate of box.
     */
    private double getYcoord(double rowNumber) {
        return ( (getHeight() / 2) - ( getContainerHeight() / 2) + rowNumber * BOX_SIZE + rowNumber * BOX_SPACING );
    }

    /**
     *
     * @param colNumber The sequence number of box in the column.
     * @return X coordinate of box.
     */
    public double getXcoord(double colNumber) {
        return ( (getWidth() / 2) - ( getContainerWidth() / 2) + colNumber * BOX_SIZE + colNumber * BOX_SPACING );
    }

    /**
     * Calculating width of matrix.
     * *matrix = container with boxs.
     * @return The width of matrix.
     */
    public double getContainerWidth() {
        double containerWidth = NUM_COLS*BOX_SIZE + NUM_COLS*BOX_SPACING;
        return containerWidth;
    }

    /**
     * Calculating height of matrix.
     * *matrix = container with boxs.
     * @return The height of matrix.
     */
    public double getContainerHeight() {
        double containerHeight = NUM_ROWS*BOX_SIZE + NUM_ROWS*BOX_SPACING;
        return containerHeight;
    }
}
