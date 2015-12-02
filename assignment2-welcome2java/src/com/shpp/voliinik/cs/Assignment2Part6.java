package com.shpp.voliinik.cs;

import acm.graphics.GArc;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    /**
     * Global variables for length and size of Caterpillar/
     */
    public static final int CATERPILLAR_LENGTH = 18;
    public static final int CATERPILLAR_SIZE = 100;

    /**
     * Global variables for margin
     */
    public double MARGIN_TOP = CATERPILLAR_SIZE / 3;
    public double MARGIN_LEFT = 0;

//////////////////////////////////////////////////////////////////////////////////////////////////
    /* this part i have cope right from here: http://www.cyberforum.ru/java/thread463834.html */
    /**
     * Getting height & width of screen where runned this program.
     */
    public static Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int vert = sSize.height;
    public static int hor = sSize.width;
//////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Global variables for width and height of program window.
     * Automaticly calculated.
     */
    public static final int APPLICATION_WIDTH = hor - 200;
    public static final int APPLICATION_HEIGHT = CATERPILLAR_SIZE + 150;

    /**
     * Global variables for coords of body.
     */
    public double xСoordOfBodyPart = 0;
    public double yСoordOfBodyPart = 0;

    /**
     * Global set of objects which used in drawing Caterpillar
     */
    GOval bodyPart;
    GOval head;

    GOval leftEye;
    GOval leftPupil;
    GOval rightPupil;
    GOval rightEye;
    GArc smile;

    /**
     * Main method.
     * Calling Caterpillar creating method
     */
    public void run() {
        createCaterpillar();
    }

    /**
     * Caterpillar creator.
     * Calling creating method to each part of Caterpillar;
     * Calling stylizing method to each part of Caterpillar;
     * Calling method which draws each part of Caterpillar;
     * <p>
     * If Caterpillar out of screen
     * calling printing error message method;
     */
    private void createCaterpillar() {
        for (int i = 0; i < (CATERPILLAR_LENGTH); i++) {
            if (i == CATERPILLAR_LENGTH - 1) {
                createHead();
                stylizeHead();
                drawHead(i);
            } else {
                createPartOfBody();
                stylizeBodyPart();
                drawBodyPart(i);
            }
        }
        if (isCaterpillarBiggerThanScreen()) {
            showMyErrorMessage();
        }
    }

    /**
     * Head creator.
     * Creating head Calling method;
     * Calling eyes creating method;
     * Calling smile creating method;
     */
    private void createHead() {
        head = new GOval(CATERPILLAR_SIZE, CATERPILLAR_SIZE);
        createEyesOfCaterpillar();
        createSmileOfCaterpillar();
    }

    /**
     * Eyes creating method.
     * Creating teft & right eyes of Caterpillar;
     */
    private void createEyesOfCaterpillar() {
        leftEye = new GOval(CATERPILLAR_SIZE / 3, CATERPILLAR_SIZE / 2.8);
        rightEye = new GOval(CATERPILLAR_SIZE / 3, CATERPILLAR_SIZE / 2.8);

        leftPupil = new GOval(leftEye.getWidth() / 2, leftEye.getWidth() / 2);
        rightPupil = new GOval(leftEye.getWidth() / 2, leftEye.getWidth() / 2);
    }

    /**
     * Smile creating method.
     * Creating smile of Caterpillar;
     */
    private void createSmileOfCaterpillar() {
        smile = new GArc(CATERPILLAR_SIZE * 0.7, CATERPILLAR_SIZE * 0.7, -120, 60);
    }

    /**
     * Head stylizing method.
     * Stylizing head;
     * Calling stylizing method for eyes;
     */
    private void stylizeHead() {
        Color HEAD_GREEN = new Color(58, 142, 0);
        Color HEAD_DARKGREEN = new Color(11, 66, 3);
        head.setColor(HEAD_DARKGREEN);
        head.setFilled(true);
        head.setFillColor(HEAD_GREEN);

        stylizeEyes();
    }

    /**
     * Eyes stylizing method
     * Stylizing eyes;
     * Calling pupil stylizing method;
     */
    private void stylizeEyes() {
        Color COLOR_EYE = new Color(168, 218, 150);
        leftEye.setColor(Color.BLACK);
        leftEye.setFilled(true);
        leftEye.setFillColor(COLOR_EYE);

        rightEye.setColor(Color.BLACK);
        rightEye.setFilled(true);
        rightEye.setFillColor(COLOR_EYE);

        stylizePupils();
    }

    /**
     * Pupil stylizing method.
     * Stylizing pupils;
     */
    private void stylizePupils() {
        Color COLOR_PUPIL = new Color(36, 49, 34);
        leftPupil.setColor(Color.BLACK);
        leftPupil.setFilled(true);
        leftPupil.setFillColor(COLOR_PUPIL);

        rightPupil.setColor(Color.BLACK);
        rightPupil.setFilled(true);
        rightPupil.setFillColor(COLOR_PUPIL);
    }

    /**
     * Head drawing method.
     * Getting (x,y) coords where need to draw head;
     * Drawing head;
     * Calling drawing method for eyes;
     * Calling drawing method for smile;
     *
     * @param i - number of body part which must be head;
     */
    private void drawHead(int i) {
        double xCoordOfHead = getXcoordOfBodyPart(i);
        double yCoordOfHead = getYcoordOfBodyPart(i);

        add(head, xCoordOfHead, yCoordOfHead);
        addEyes(xCoordOfHead, yCoordOfHead);
        addSmile(xCoordOfHead, yCoordOfHead);

    }

    /**
     * Eyes drawing method.
     * <p>
     * Getting (x, y) coords of eyes;
     * Drawing eyes;
     * Getting (x, y) coords of pupils;
     * Drawing pupils;
     *
     * @param xCoordOfHead - X coord of head (on which need to draw eyes)
     * @param yCoordOfHead - Y coord of head (on which need to draw eyes)
     */
    private void addEyes(double xCoordOfHead, double yCoordOfHead) {
        double xCoordOfLeftEye = xCoordOfLeftEye(xCoordOfHead);
        double xCoordOfRightEye = xCoordOfRightEye(xCoordOfHead);
        double yCoordOfForehead = getYcoordOfForehead(yCoordOfHead);

        add(leftEye, xCoordOfLeftEye, yCoordOfForehead);
        add(rightEye, xCoordOfRightEye, yCoordOfForehead);

        double xCoordOfLeftPupil = xCoordOfLeftPupil(xCoordOfLeftEye);
        double xCoordOfRightPupil = xCoordOfRightPupil(xCoordOfRightEye);
        double yCoordOfPupils = getYCoordOfPupils(yCoordOfForehead);

        add(leftPupil, xCoordOfLeftPupil, yCoordOfPupils);
        add(rightPupil, xCoordOfRightPupil, yCoordOfPupils);
    }

    /**
     * Getting x coord of left eye.
     *
     * @param xCoordOfHead - X coord of head (on which need to draw eyes)
     * @return x coord of left eye.
     */
    private double xCoordOfLeftEye(double xCoordOfHead) {
        double leftEyeX = xCoordOfHead - (CATERPILLAR_SIZE * 0.35) + (CATERPILLAR_SIZE / 2) - (leftEye.getWidth() / 2);
        return leftEyeX;
    }

    /**
     * Getting x coord of right eye.
     *
     * @param xCoordOfHead - X coord of head (on which need to draw eyes)
     * @return x coord of right eye.
     */
    private double xCoordOfRightEye(double xCoordOfHead) {
        double rightEyeX = xCoordOfHead + (CATERPILLAR_SIZE * 0.35) + (CATERPILLAR_SIZE / 2) - (rightEye.getWidth() / 2);
        return rightEyeX;
    }

    /**
     * Getting y coord of eyes.
     *
     * @param yCoordOfHead - y coord of head (on which need to draw eyes)
     * @return y coord of eyes.
     */
    private double getYcoordOfForehead(double yCoordOfHead) {
        double foreheadY = yCoordOfHead - (CATERPILLAR_SIZE * 0.05);
        return foreheadY;
    }

    /**
     * Getting x coord of left pupil.
     *
     * @param xCoordOfLeftEye - x coord of letf eye (on which need to draw pupil)
     * @return
     */
    private double xCoordOfLeftPupil(double xCoordOfLeftEye) {
        return xCoordOfLeftEye + leftEye.getWidth() / 2 - leftPupil.getWidth() / 2;
    }

    /**
     * Getting x coord of right pupil.
     *
     * @param xCoordOfRightEye - x coord of right eye (on which need to draw pupil)
     * @return x coord of right pupil.
     */
    private double xCoordOfRightPupil(double xCoordOfRightEye) {
        return xCoordOfRightEye + rightEye.getWidth() / 2 - rightPupil.getWidth() / 2;
    }

    /**
     * Getting y coord of pupils.
     *
     * @param yCoordOfForehead - x coord of eyes (on which need to draw pupil)
     * @return y coord of pupils.
     */
    private double getYCoordOfPupils(double yCoordOfForehead) {
        return yCoordOfForehead * 1.2;
    }

    /**
     * Drawing smile method.
     * Getting (x, y) coords of smile;
     * Drawing smile;
     *
     * @param xCoordOfHead - X coord of head (on which need to draw eyes)
     * @param yCoordOfHead - Y coord of head (on which need to draw eyes)
     */
    private void addSmile(double xCoordOfHead, double yCoordOfHead) {

        double xCoordOfSmile = getXcoordOfSmile(xCoordOfHead);
        double yCoordOfSmile = getYcoordOfSmile(yCoordOfHead);
        add(smile, xCoordOfSmile, yCoordOfSmile);
    }

    /**
     * Getting x coord of smile;
     *
     * @param xCoordOfHead - X coord of head (on which need to draw eyes)
     * @return x coord of smile;
     */
    private double getXcoordOfSmile(double xCoordOfHead) {
        return xCoordOfHead + head.getHeight() / 2 - smile.getWidth();
    }

    /**
     * Getting y coord of smile;
     *
     * @param yCoordOfHead - Y coord of head (on which need to draw eyes)
     * @return y coord of smile;
     */
    private double getYcoordOfSmile(double yCoordOfHead) {
        return yCoordOfHead - smile.getHeight();
    }

    /**
     * Creating part of body.
     */
    private void createPartOfBody() {
        bodyPart = new GOval(CATERPILLAR_SIZE, CATERPILLAR_SIZE);
    }

    /**
     * Stylizing part of body.
     */
    private void stylizeBodyPart() {
        Color GREEN = new Color(71, 172, 0);
        Color DARKGREEN = new Color(22, 83, 0);
        bodyPart.setColor(DARKGREEN);
        bodyPart.setFilled(true);
        bodyPart.setFillColor(GREEN);
    }

    /**
     * Getting (x, y) coord of body part where need to draw body;
     * Drawing part of body.
     *
     * @param i - number of body part which drawing;
     */
    private void drawBodyPart(int i) {
        double xCoord = getXcoordOfBodyPart(i);
        double yCoord = getYcoordOfBodyPart(i);
        add(bodyPart, xCoord, yCoord);
    }

    /**
     * Checking if Caterpillar out of screen.
     *
     * @return
     */
    public boolean isCaterpillarBiggerThanScreen() {
        if ((head.getX() + head.getWidth() >= getWidth()) ||
                (head.getY() + head.getHeight() >= getHeight()) ||
                (bodyPart.getX() + bodyPart.getWidth() >= getWidth()) ||
                (bodyPart.getY() + bodyPart.getHeight() >= getHeight())) {
            return true;
        }
        return false;
    }

    /**
     * Printin error message method.
     * Setting error string;
     * Stylizing error string;
     * Getting (x, y) coords where need printing error string;
     * Printing error string;
     */
    private void showMyErrorMessage() {
        String stringToPrint = "Length of your caterpillar out of screen! :c \n\r So you can't see all beauty of this caterpillar! :C";
        GLabel errorString = new GLabel(stringToPrint);
        errorString.setFont(new Font("Century Gothic", Font.BOLD, 20));
        errorString.setColor(Color.BLACK);

        double x = getStringXCoord(errorString);
        double y = getStringYCoord(errorString);
        add(errorString, x, y);
    }

    /**
     * Getting x coord of error string;
     *
     * @param errorString - error string;
     * @return x coord of error string;
     */
    private double getStringXCoord(GLabel errorString) {
        return ((getWidth() - errorString.getWidth()) / 2);
    }

    /**
     * Getting y coord of error string;
     *
     * @param errorString - error string;
     * @return y coord of error string;
     */
    private double getStringYCoord(GLabel errorString) {
        return errorString.getHeight() * 1.1;
    }

    /**
     * Getting x coord of body part;
     *
     * @param i - number of body part which drawing;
     * @return x coord of body part;
     */
    public double getXcoordOfBodyPart(int i) {
        xСoordOfBodyPart += CATERPILLAR_SIZE * 0.5;
        return xСoordOfBodyPart;
    }

    /**
     * Getting y coord of body part;
     *
     * @param i - number of body part which drawing;
     * @return y coord of body part;
     */
    private double getYcoordOfBodyPart(int i) {
        if (i % 2 == 0) {
            yСoordOfBodyPart = (+1) * CATERPILLAR_SIZE * 0.1;
        } else {
            yСoordOfBodyPart = (-1) * CATERPILLAR_SIZE * 0.1;
        }
        return yСoordOfBodyPart + MARGIN_TOP;
    }
}