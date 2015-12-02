package com.shpp.voliinik.cs;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {

    /** Width of flag rect */
    public static final int FLAGSIZE = 500;

    /** Country for which program will draw the flag */
    /* For today it is only GERMANY
     * If you want to add some country for this program you need to:
     *      - change global variable COUNTRY;
     *      - write some new code in "customizeFlag" method for customizing flag what you want;
     *      */
    public static final String COUNTRY = "GERMANY";

    /** The aspect ratio of the rectangle (NOT REAL SIZE)*/
    /* 5:4 for GERMANY */
    public static final int FLAGWIDTH = 5;
    public static final int FLAGHEIGHT = 3;

    /** Height of flag title */
    /* (by default: automatically calculated) */
    public static final int TEXTHEIGHT = FLAGSIZE/30;

    /** Main method
     * Calling creating method.
     * Calling method for print title of country.
     * */
    public void run() {
        createFlag();
        printTitle();
    }

    /** Printing title
     * Calling customizing method for string.
     * Adding string to screen.
     * */
    private void printTitle() {
        String stringToPrint = "Flag of "+COUNTRY;
        GLabel title = new GLabel(stringToPrint);

        customizeString(title);

        add(title);
    }

    /** Customize string method
     * Customizing string.
     *
     * @param title - string which contains info about flag
     */
    private void customizeString(GLabel title) {
        title.setFont(new Font("Century Gothic", Font.BOLD, TEXTHEIGHT));
        title.setColor(Color.BLACK);

        double stringWidth = title.getWidth();
        title.setLocation((getWidth()-stringWidth*1.05), (getHeight()-TEXTHEIGHT*0.7));
    }

    /** Create flag method
     * Сreating frame flag & put it to center position.
     * Calling customizing method for flags.
     * Draw flag.
     * */
    private void createFlag() {

        double flagStripWidth = getFlagWidth();
        double flagStripHeight = getFlagHeight() / 3;

        double x = getXStartCoord(flagStripWidth); // for width
        double y = getYStartCoord(flagStripHeight); // for height

        GRect firstStripOfFlag = new GRect(x, y + flagStripHeight * 0, flagStripWidth, flagStripHeight);
        GRect secondStripOfFlag = new GRect(x, y + flagStripHeight * 1, flagStripWidth, flagStripHeight);
        GRect thirdStripOfFlag = new GRect(x, y + flagStripHeight * 2, flagStripWidth, flagStripHeight);

        customizeFlag(firstStripOfFlag, secondStripOfFlag, thirdStripOfFlag);

        drawFlag(firstStripOfFlag, secondStripOfFlag, thirdStripOfFlag);
    }

    /**Customize flag method
     * Setting 'filled' to true for each flag strip.
     * Checking for which country need to customize flag.
     * Customize flag
     *
     * @param firstStrip - link to first strip of flag
     * @param secondStrip - link to second strip of flag
     * @param thirdStrip - link to third strip of flag
     */
    private void customizeFlag(GRect firstStrip, GRect secondStrip, GRect thirdStrip) {

        firstStrip.setFilled(true);
        secondStrip.setFilled(true);
        thirdStrip.setFilled(true);

        if (COUNTRY.equals("GERMANY")) {

            Color GERMANY_BLACK = new Color(0, 0, 0);
            Color GERMANY_RED = new Color(255, 0, 0);
            Color GERMANY_YELLOW = new Color(255, 204, 0);

            firstStrip.setFillColor(GERMANY_BLACK);
            firstStrip.setColor(GERMANY_BLACK);

            secondStrip.setFillColor(GERMANY_RED);
            secondStrip.setColor(GERMANY_RED);

            thirdStrip.setFillColor(GERMANY_YELLOW);
            thirdStrip.setColor(GERMANY_YELLOW);
        }
    }


    /** Draw flag method
     * Drawing flag
     *
     * @param firstStrip - link to first strip of flag
     * @param secondStrip - link to second strip of flag
     * @param thirdStrip - link to third strip of flag
     */
    private void drawFlag(GRect firstStrip, GRect secondStrip, GRect thirdStrip) {
        add(firstStrip);
        add(secondStrip);
        add(thirdStrip);
    }


    /**
     * Getting X coord of left-upper corner of flag.
     *
     * @param flagStripWidth - width of flag strip (equal flag width)
     * @return X coord of left-upper corner of flag
     */
    private double getXStartCoord(double flagStripWidth) {
        return ((getWidth() / 2) - flagStripWidth / 2);
    }

    /**
     *  Getting y coord of left-upper corner of flag.
     * @param flagStripHeight - - height of flag strip (NOT FLAG HEIGHT)
     * @return Y coord of left-upper corner of flag
     */
    private double getYStartCoord(double flagStripHeight) {
        return ((getHeight() / 2) - flagStripHeight*3 / 2 - TEXTHEIGHT/2);
    }

    /** Getting width of flag */
    public double getFlagWidth() {
        return FLAGSIZE;
    }

    /** Calculating height of flag */
    public double getFlagHeight() {
        return ((FLAGSIZE / FLAGWIDTH) * FLAGHEIGHT);
    }
}