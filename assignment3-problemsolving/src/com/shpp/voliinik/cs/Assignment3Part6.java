package com.shpp.voliinik.cs;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part6 extends WindowProgram {

    /** Declaring global variables. */

    // Setting application width.
    public static final int APPLICATION_WIDTH = 720;
    // Calculating application height.
    public static final int APPLICATION_HEIGTH = (APPLICATION_WIDTH / 16) * 9;
    // Calculating a single segment of virtual "grid" of application.
    public static final double UNIT = APPLICATION_WIDTH / 16;
    // Calculating pause time for 60 fps.
    public static final double PAUSE_TIME = 1000.0 / 60;

    /** Setting colors to circles */
    private static final Color FIRST_CIRCLE_COLOR = new Color(202, 62, 58);
    private static final Color SECOND_CIRCLE_COLOR = new Color(255, 225, 109);
    private static final Color THIRD_CIRCLE_COLOR = new Color(39, 174, 96);
    private static final Color FOURTH_CIRCLE_COLOR = new Color(255, 255, 255);

    /** Setting magic factor, so everything was fine.*/
    private static final double MAGIC_NUMBER = 0.7;

    /** Declaring widescreen rectangles. */
    public GRect blackField1;
    public GRect blackField2;
    /** Creating random generator */
    RandomGenerator rgen = RandomGenerator.getInstance();

    /**
     * Main method.
     *
     * Calling starting method.
     */
    public void run() {
        hereWeGo();
    }

    /**
     * Starter method.
     * Creating intro.
     * Adding logo.
     * Adding outro.
     */
    private void hereWeGo() {
        makeIntro();           // 2 sec
        addLogo();                  // 1.5 sec
        makeOutro();                // <1 sec
    }

    /**
     * Making intro method.
     *
     * In first part declaring four circles for intro "explosion".
     * In second part declaring four timers for each circle &
     *      starting cycle in which have "explosion".
     *
     * In WHILE cycle we have:
     *      four "if" for each circle,
     *      one "if" for widescreen.
     *
     * It is necessary to make right time delay between frames.
     * In WHILE we draw only one frame, so we need to do a lot of frames.
     */
    private void makeIntro() {
        /* START OF FIRST PART */
        // Creating and stylizing first circle.
        GOval exPart1 = new GOval(getWidth() / 2, getHeight() / 2, 0, 0);
        exPart1.setFilled(true);
        exPart1.setColor(FIRST_CIRCLE_COLOR);
        add(exPart1);
        exPart1.setVisible(false);

        // Creating and stylizing secong circle.
        GOval exPart2 = new GOval(getWidth() / 2, getHeight() / 2, 0, 0);
        exPart2.setFilled(true);
        exPart2.setColor(SECOND_CIRCLE_COLOR);
        add(exPart2);
        exPart2.setVisible(false);

        // Creating and stylizing third circle.
        GOval exPart3 = new GOval(getWidth() / 2, getHeight() / 2, 0, 0);
        exPart3.setFilled(true);
        exPart3.setColor(THIRD_CIRCLE_COLOR);
        add(exPart3);
        exPart3.setVisible(false);

        // Creating and stylizing fourth circle.
        GOval exPart4 = new GOval(getWidth() / 2, getHeight() / 2, 0, 0);
        exPart4.setFilled(true);
        exPart4.setColor(FOURTH_CIRCLE_COLOR);
        add(exPart4);
        exPart4.setVisible(false);
        /* END OF FIRST PART */

        /* START OF SECOND PART */
        // Declaring timers.
        int timer = 0;
        int timer2 = 0;
        int timer3 = 0;
        int timer4 = 0;

        // Adding widescreen
        stylizeBlackFields();
        add(blackField1, 0, (0 - blackField2.getHeight() * 1.05));
        add(blackField2, 0, getHeight() * 1.01);

        // Starting draw the frames.
        // 120 frames here:
        while (timer < 120) {

            // Moving widescreen 20 frames. (1/3 seconds)
            if (timer > 20) {
                if ((timer % 2 == 0) || (timer % 3 == 0)) {
                    blackField1.move(0, 1);
                    blackField2.move(0, -1);
                    sendBlackFieldsToRightPos();
                }
            }

            // Adding first circle in 0 frame. (in 0 second)
            if (timer >= 0) {
                if (timer == 0) {
                    exPart1.setVisible(true);
                    sendBlackFieldsToRightPos();
                }
                exPart1.setSize(timer * timer, timer * timer);
                exPart1.move(-(timer), -(timer));
            }

            // Adding second circle on 16th frame. (in ~1/3 second)
            if (timer > 15) {
                if (timer == 16) {
                    exPart2.setVisible(true);
                    sendBlackFieldsToRightPos();
                }
                exPart2.setSize(timer2 * timer2, timer2 * timer2);
                exPart2.move(-(timer2), -(timer2));
                timer2++;
            }

            // Adding third circle on 50th frame, (in ~1 second)
            if (timer > 50) {
                if (timer == 51) {
                    exPart3.setVisible(true);
                    sendBlackFieldsToRightPos();
                }
                exPart3.setSize(timer3 * timer3, timer3 * timer3);
                exPart3.move(-(timer3), -(timer3));
                timer3++;
            }

            // Adding fourth circle on 80th frame. (in ~1.3 second)
            if (timer > 80) {
                if (timer == 81) {
                    exPart4.setVisible(true);
                    sendBlackFieldsToRightPos();
                }
                exPart4.setSize(timer4 * timer4, timer4 * timer4);
                exPart4.move(-(timer4), -(timer4));
                timer4++;
            }

            //make pause between frames. (by default: 1/60 second for 60fps)
            pause(PAUSE_TIME);
            timer++;
        } // end of while
        /* END OF SECOND PART */
    }

    /**
     * Adding logo method.
     *
     * In first part declaring objects.
     * In second part calculating coordinates of SH ('Ш') letter.
     * In third part (in while) adding letter amd two pluses.
     */
    private void addLogo() {

        /* START OF FIRST PART */
        // Making a two pluses. (four part for two pluses)
        GRect firstPlusPart1 = new GRect(UNIT, UNIT * 3); // first "+" part 1
        stylizePlus(firstPlusPart1);
        GRect firstPlusPart2 = new GRect(UNIT * 3, UNIT); // first "+" part
        stylizePlus(firstPlusPart2);
        GRect secondPlusPart1 = new GRect(UNIT, UNIT * 3); // second "+" part 1
        stylizePlus(secondPlusPart1);
        GRect secondPlusPart2 = new GRect(UNIT * 3, UNIT); // second "+" part 2
        stylizePlus(secondPlusPart2);
        /* END OF FIRST PART */
        //let`s move it

        Color BLACK = new Color(44, 62, 80);

        /* START OF SECOND PART */
        double xSHpart1 = UNIT * 1;
        double ySHpart1 = UNIT * 3;

        double xSHpart2 = UNIT * 3;
        double ySHpart2 = UNIT * 5;

        double xSHpart3 = UNIT * 5;
        double ySHpart3 = UNIT * 3;

        double xSHpart4 = UNIT * 1;
        double ySHpart4 = UNIT * 7;
        /* END OF SECOND PART */

        /* START OF THIRD PART */

        int timer = 0;

        while (timer < 90) { // 90 frames, 1.5 seconds for adding logo.
            // adding points inside 'Ш' letter
            if (timer < 60) { // 60 frames, 1 second for drawing 'Ш' letter.

                for (int i = timer; i < 60; i++) { // For each frame adding pixels.

                    // Plotting TWO pixles inside first part of 'Ш' letter.
                    for (int j = 0; j < 2; j++) {
                        plotPixel(xSHpart1 + (rgen.nextDouble(0.0, 1.0) * UNIT),
                                (ySHpart1 + (rgen.nextDouble(0.0, 1.0) * UNIT * 4)), BLACK, 1);
                    }

                    // Plotting ONE pixle inside second part of 'Ш' letter. (coz second part ~twice smaller than others)
                    plotPixel(xSHpart2 + (rgen.nextDouble(0.0, 1.0) * UNIT),
                            (ySHpart2 + (rgen.nextDouble(0.0, 1.0) * UNIT * 2)), BLACK, 1);

                    // Plotting TWO pixles inside third part of 'Ш' letter.
                    for (int j = 0; j < 2; j++) {
                        plotPixel(xSHpart3 + (rgen.nextDouble(0.0, 1.0) * UNIT),
                                (ySHpart3 + (rgen.nextDouble(0.0, 1.0) * UNIT * 4)), BLACK, 1);
                    }

                    // Plotting TWO pixles inside fourth part of 'Ш' letter.
                    for (int j = 0; j < 2; j++) {
                        plotPixel(xSHpart4 + (rgen.nextDouble(0.0, 1.0) * UNIT * 5),
                                (ySHpart4 + (rgen.nextDouble(0.0, 1.0) * UNIT)), BLACK, 1);
                    }

                }
            }

            // adding pluses
            if (timer == 60) {
                add(firstPlusPart1, UNIT * 8.5, UNIT * 4);
                add(firstPlusPart2, UNIT * 7.5, UNIT * 5);
            }
            if (timer == 80) {
                add(secondPlusPart1, UNIT * 12.5, UNIT * 4);
                add(secondPlusPart2, UNIT * 11.5, UNIT * 5);
            }


            pause(PAUSE_TIME);
            timer++;
        }
        /* END OF THIRD PART */
    }

    /**
     * Making outro method.
     *
     * Here jost creale one big rect with 100% opacity
     *      and in cycle adding opacity to 0% to it.
     *      (Transparent into opaque.)
     *
     *
     */
    private void makeOutro() {
        GRect outRect = new GRect(-UNIT, -UNIT, UNIT * 18, UNIT * 11);
        outRect.setFilled(true);
        add(outRect);
        outRect.sendToFront();
        int timer = 0;
        int alpha;

        while (timer < 90) {
            alpha = (int) (0 + (timer * 2.87));
            Color COLOR = new Color(0, 0, 0, alpha);
            outRect.setFillColor(COLOR);
            pause(PAUSE_TIME*MAGIC_NUMBER);
            timer++;
        }
    }

    /**
     * Stylizing pluses part.
     *
     * @param plusPart - Part of plus.
     */
    private void stylizePlus(GRect plusPart) {
        Color GREEN = new Color(39, 174, 96);
        plusPart.setFilled(true);
        plusPart.setColor(GREEN);
        plusPart.setFillColor(GREEN);
    }

    /**
     *  Sending widescreen to front.
     *  It is necessary to make a good widescreen in animation.
     *
     *  */
    private void sendBlackFieldsToRightPos() {
        blackField1.sendToFront();
        blackField2.sendToFront();
    }

    /**
     * Stylizing widescreen rectangles.
     */
    private void stylizeBlackFields() {
        blackField1 = new GRect(getWidth(), UNIT * 3);
        blackField1.setFilled(true);
        blackField1.setFillColor(Color.BLACK);

        blackField2 = new GRect(getWidth(), UNIT * 3);
        blackField2.setFilled(true);
        blackField2.setFillColor(Color.BLACK);
    }

    /**
     * Plots a pixel at the specified (x, y) coordinate.
     * (c) Ш++
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    private void plotPixel(double x, double y, Color color, int size) {
        /* Create a 1x1 pixel of the given color. */
        GRect pixel = new GRect(x, y, size, size);
        pixel.setFilled(true);
        pixel.setColor(color);
        add(pixel);
        pixel.sendToFront();
        sendBlackFieldsToRightPos();
    }
}