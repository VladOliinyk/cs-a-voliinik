package com.shpp.voliinik.cs;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part3 extends  WindowProgram {

    /*
    * Constants controlling the relative positions of the
    * three toes to the upper-left corner of the pawprint.
    */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left corner of the pawprint. */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The default width and height of the window. */
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    /**
     * main method
     *
     * twice calling drawing Pawprint methods with offset parameters
     * */
    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /**
     * drawing Pawprint methods
     * calling two methods which draw toes and heel
     *
     * @param xOffset The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param yOffset The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double xOffset, double yOffset) {

        drawToes(xOffset, yOffset);
        drawHeel(xOffset, yOffset);
    }

    /**
     * drawing heel method
     *
     * draws a heel
     *
     * @param xOffset The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param yOffset The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawHeel(double xOffset, double yOffset) {
        GOval heel = new GOval( (xOffset + HEEL_OFFSET_X), ( yOffset + HEEL_OFFSET_Y), HEEL_WIDTH, HEEL_HEIGHT);

        heel.setFilled(true);
        heel.setColor(Color.BLACK);
        heel.setFillColor(Color.BLACK);
        add(heel);
    }

    /**
     * drawign toes method
     *
     * calculating offsets for each toe (create local variables for it)
     * calling main single toe draw method
     *
     * @param xOffset The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param yOffset The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawToes(double xOffset, double yOffset) {

        double xLocalOffset;
        double yLocalOffset;

        /*
        * use cycle to draw three toe
        * the cycle is NOT FLEXIBLE. It is intended for drawing only three fingers.
        * */
        for (int i = 0; i < 3; i++) {

            int toeNumber = i; // I did it for understandability. I know that i can use the "i" counter.

            xLocalOffset = xOffset;
            yLocalOffset = yOffset;

            /* calculating X and Y coord for each toe */
            if (toeNumber == 0) {
                xLocalOffset += FIRST_TOE_OFFSET_X;
                yLocalOffset += FIRST_TOE_OFFSET_Y;
            } else if (toeNumber == 2) {
                xLocalOffset += SECOND_TOE_OFFSET_X;
                yLocalOffset += SECOND_TOE_OFFSET_Y;
            } else {
                xLocalOffset += THIRD_TOE_OFFSET_X;
                yLocalOffset += THIRD_TOE_OFFSET_Y;
            }

            /* calling drawing method for each toe*/
            drawOneToe(xLocalOffset, yLocalOffset);
        }
    }

    /**
     * toe drawing method
     *
     * drawing one toe
     *
     * @param xOffset The x coordinate of the upper-left corner of the toe.
     * @param yOffset The y coordinate of the upper-left corner of the toe.
     */
    private void drawOneToe(double xOffset, double yOffset) {
        GOval toe = new GOval(xOffset, yOffset, TOE_WIDTH, TOE_HEIGHT);

        toe.setFilled(true);
        toe.setColor(Color.BLACK);
        toe.setFillColor(Color.BLACK);
        add(toe);
    }
}
