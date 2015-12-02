package com.shpp.voliinik.cs;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends  WindowProgram {

    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    /**
     * set a circle diameter in a 1:3 ratio to the width of the window
     * use the commented version if you want to use a static diameter
     */
     public static final int CIRCLE_DIAMETER = APPLICATION_WIDTH/3;
     // public static final int CIRCLE_DIAMETER = 100;

    /**
     * main method
     * calling main draw method
     * */
    public void run() {
        letUsDraw();
    }

    /**
     * main draw method
     * calling drawing circles method
     * calling drawing rectangle method
     * */
    private void letUsDraw() {
        drowAllCircles();
        drowRectangle();
    }

    /**
     * drawing rectangle method
     *
     * drawing a rectangle
     * */
    private void drowRectangle() {
        double xyCoord = CIRCLE_DIAMETER/2;
        double rectWidth = getWidth()-CIRCLE_DIAMETER;
        double rectHeight = getHeight()-CIRCLE_DIAMETER;

        GRect rect = new GRect(xyCoord, xyCoord, rectWidth, rectHeight);
        rect.setColor(Color.WHITE);
        rect.setFillColor(Color.WHITE);
        rect.setFilled(true);
        add(rect);
    }

    /**
     * drawing circles method
     *
     * calling drawing circle method with iteration parameter
     */
    private void drowAllCircles() {
        for (int i=0; i < 4; i++) {
            drawOneCircle(i);
        }
    }

    /**
     * drawing circle method
     *
     * calculating parameters for circle
     * drawing a circle
     * @param circleNumber
     */
    private void drawOneCircle(int circleNumber) {
        double xCoord;
        double yCoord;

        if (circleNumber == 0) {
            xCoord = 0;
            yCoord = 0;
        } else if (circleNumber == 1) {
            xCoord = getWidth() - CIRCLE_DIAMETER;
            yCoord = 0;
        } else if (circleNumber == 2) {
            xCoord = 0;
            yCoord = getHeight() - CIRCLE_DIAMETER;
        } else {
            xCoord = getWidth() - CIRCLE_DIAMETER;
            yCoord = getHeight() - CIRCLE_DIAMETER;
        }
        GOval circle = new GOval(xCoord, yCoord, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        circle.setFillColor(Color.BLACK);
        circle.setFilled(true);
        add(circle);
    }

}