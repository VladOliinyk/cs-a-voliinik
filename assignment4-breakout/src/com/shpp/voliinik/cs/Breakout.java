package com.shpp.voliinik.cs;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    GRect paddle;
    GOval ball;

    /**
     * X and Y velocity
     */
    private double vx = 2.0, vy = 0;

    /**
     * Frame rate for 1 second. Here - 60 fps.
     */
    int PAUSE_TIME = 1000 / 60;

    /**
     * Counter for colors  will be filled bricks.
     */
    private int colorCounter = 0;

    /**
     * Live counter.
     */
    private int LIFE = NTURNS;

    /**
     * Brick counter.
     */
    private int BRICK_COUNTER = NBRICK_ROWS * NBRICKS_PER_ROW;

    /**
     * Difficulty slider.
     */
    JSlider diffSlider = new JSlider(1, 5, 1);

    /**
     * Init method.
     * Adding slider on the top of the screen.
     */
    public void init() {
        JLabel text = new JLabel("Difficulty: ");
        add(text, NORTH);
        text = new JLabel("1");
        add(text, NORTH);
        add(diffSlider, NORTH);
        text = new JLabel("5");
        add(text, NORTH);
    }

    /**
     * Main method.
     * <p>
     * Adding mouse listeners.
     * Creating paddle, bricks and ball.
     * <p>
     * Moving ball while not end of game.
     * <p>
     * Calling GameOver or YouWin screans.
     */
    public void run() {

        addMouseListeners();
        createPaddle();
        createBricks();
        createBall();
        while ((LIFE > 0) && (BRICK_COUNTER != 0)) {
            moveBall();
        }
        if (LIFE == 0) {
            gameOver();
        }
        if (BRICK_COUNTER == 0) {
            youWin();
        }

    }

    /**
     * Creating paddle method.
     * Create paddle.
     * Adding paddle to the screen.
     */
    private void createPaddle() {
        paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setFillColor(Color.BLACK);
        add(paddle,
                getWidth() / 2 - PADDLE_WIDTH / 2,
                getHeight() - PADDLE_Y_OFFSET
        );
    }

    /**
     * Creating bricks method.
     * Create bricks.
     * Stylizing bricks:
     * calling changeBrickColor method to change color for each odd row.
     * Adding bricks to the screen.
     */
    private void createBricks() {
        Color brickColor = Color.RED;
        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                drawBrick(i, j, brickColor);
            }
            if (i % 2 == 1) {
                brickColor = changeBrickColor(brickColor);
            }
        }
    }

    /**
     * Draw brick method.
     * <p>
     * Creating rectangle with necessary params.
     * Adding brick to the screen.
     *
     * @param rowNumber  - Number of row which need to draw.
     * @param colNumber  - Number of column (number of brick in row) which need to draw.
     * @param brickColor - Color of brick which need to draw.
     */
    private void drawBrick(int rowNumber, int colNumber, Color brickColor) {
        double xCoord = getXcoord(colNumber);
        double yCoord = getYcoord(rowNumber);

        GRect brick = new GRect(xCoord, yCoord, BRICK_WIDTH, BRICK_HEIGHT);

        brick.setFilled(true);
        brick.setColor(brickColor);
        brick.setFillColor(brickColor);
        add(brick);
    }

    /**
     * Getting x coord of brick by column number.
     *
     * @param colNumber number of column which have that brick.
     * @return X coord of brick.
     */
    private double getXcoord(int colNumber) {
        return (getWidth() / 2 -
                (NBRICKS_PER_ROW * BRICK_WIDTH) / 2 -
                ((NBRICKS_PER_ROW - 1) * BRICK_SEP) / 2 +
                BRICK_WIDTH * colNumber +
                BRICK_SEP * colNumber
        );
    }

    /**
     * Getting y coord of brick by row number.
     *
     * @param rowNumber number of row which have that brick.
     * @return Y coord of brick.
     */
    private double getYcoord(int rowNumber) {
        return (BRICK_Y_OFFSET +
                BRICK_HEIGHT * rowNumber +
                BRICK_SEP * rowNumber
        );
    }

    /**
     * Changing brick color method.
     * Changing brick color.
     * <p>
     * Change brick color for each couple of rows.
     *
     * @param brickColor - prew brick color.
     * @return new brick color.
     */
    public Color changeBrickColor(Color brickColor) {
        colorCounter++;
        while (colorCounter > 4) {
            colorCounter = colorCounter - 5;
        }
        //RED, ORANGE, YELLOW, GREEN, CYAN
        if (colorCounter == 0) brickColor = Color.RED;
        if (colorCounter == 1) brickColor = Color.ORANGE;
        if (colorCounter == 2) brickColor = Color.YELLOW;
        if (colorCounter == 3) brickColor = Color.GREEN;
        if (colorCounter == 4) brickColor = Color.CYAN;

        return brickColor;
    }

    /**
     * Create bal method.
     * Creating ball.
     * Adding ball to the center of the screen.
     */
    private void createBall() {
        ball = new GOval(BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        add(ball, getWidth() / 2, getHeight() / 2);
    }

    /**
     * Move ball method.
     * <p>
     * Moving ball.
     * <p>
     * Checking ball position and take the necessary measures if
     * ball hit one of the walls, (1)
     * ball hit the some else object. (2)
     */
    private void moveBall() {
        GObject collider = null;

        ball.move(vx, vy);

        // (1)
        if (ballHitSideWalls()) {
            vx *= -1;
        }
        if (ballHitUpWall()) {
            vy *= -1;
        }

        double x, y;

        /*
        (2)
        Here we have for which makes bypassing every point of the circle.
        And if found some colliding object behind circle do the "ricochet" from that object.
         */
        for (int i = 0; i < 360; i++) {
            x = (ball.getX() + BALL_RADIUS) + Math.cos(i) * (BALL_RADIUS + 0.5);
            y = (ball.getY() + BALL_RADIUS) + Math.sin(i) * (BALL_RADIUS + 0.5);

            if (getElementAt(x, y) != null) {
                ricochet(getElementAt(x, y));
                break;
            }
        }
        pause(PAUSE_TIME);
    }

    /**
     * Checking ball position.
     *
     * @return true or false depending from ball position. True if ball hit one of side walls.
     */
    private boolean ballHitSideWalls() {
        return ((ball.getX() <= 0) || ((ball.getX() + BALL_RADIUS * 2) >= getWidth()));
    }

    /**
     * Checking ball position.
     * If ball hit the flore calling Round Lose method.
     *
     * @return true or false depending from ball position. True if ball hit the ceiling.
     */
    private boolean ballHitUpWall() {
        if (ball.getY() + BALL_RADIUS * 2 > getHeight()) {  // for flor
            roundIsLost();
            return false;
        }
        return (ball.getY() <= 0); // for ceiling
    }

    /**
     * Round lose method.
     * <p>
     * Resets Y velocity.
     * Set ball to the center of screen.
     * Decrement LIVE counter.
     */
    private void roundIsLost() {
        vy = 0;
        ball.setLocation(getWidth() / 2, getHeight() / 2);
        LIFE--;
    }

    /**
     * Ricochet method.
     * <p>
     * Checking which object collided.
     * Calling paddle ricochet method if it is paddle.
     * Calling brick ricochet method if if is brick and remove that brick.
     *
     * @param collider
     */
    private void ricochet(GObject collider) {
        if (collider == paddle) {
            if (ball.getY() + BALL_RADIUS * 1.1 < paddle.getY()) {
                ricochetFromPaddle();
            }
        }
        if ((collider != paddle) && (collider != null) && (collider != ball)) {
            ricochetFromBrick(collider);
            remove(collider);
            BRICK_COUNTER--;
        }
    }

    /**
     * Ricochet from paddle method.
     * <p>
     * Checking which point the ball hits the paddle.
     * Depending on the result of changing the angle of rebound.
     */
    private void ricochetFromPaddle() {
        double paddleX = paddle.getX();
        double ballXCenter = ball.getX() + BALL_RADIUS;
        RandomGenerator rgen = RandomGenerator.getInstance();

        vy *= -1;
        if (ballXCenter > (paddleX + ((paddle.getWidth() / 10) * 9))) {
            if (vx < 0) {
                vx *= -1;
            }
        } else if (ballXCenter > (paddleX + ((paddle.getWidth() / 3) * 2))) {
            if (vx > 0) {
                vx = rgen.nextDouble(2.4, 2.9);
            } else {
                vx = -rgen.nextDouble(1.1, 1.6);
            }
        } else if (ballXCenter > (paddleX + ((paddle.getWidth() / 3)))) {
            if (vx > 0) {
                vx = rgen.nextDouble(1.7, 2.3);
            } else {
                vx = -rgen.nextDouble(1.7, 2.3);
            }
        } else if (ballXCenter > (paddleX + (paddle.getWidth() / 10))) {
            if (vx > 0) {
                vx = rgen.nextDouble(1.1, 1.6);
            } else {
                vx = -rgen.nextDouble(2.4, 2.9);
            }
        } else {
            if (vx > 0) {
                vx *= -1;
            }
        }
    }

    /**
     * Ricochet from brick method.
     * <p>
     * Checking ball position relatively position of the brick.
     * Depending on the result of changing the direction of rebound. (change velocity vector)
     *
     * @param collider brick on which ball hits.
     */
    private void ricochetFromBrick(GObject collider) {
        double ballXcenter = ball.getX() + BALL_RADIUS;
        double ballYcenter = ball.getY() + BALL_RADIUS;

        if ((ballXcenter < collider.getX()) || (ballXcenter > collider.getX() + BRICK_WIDTH)) {
            vx *= -1;
        }

        if ((ballYcenter > collider.getY() + BRICK_HEIGHT) || (ballYcenter < collider.getY())) {
            vy *= -1;
        }

    }

    /**
     * Game over method.
     * <p>
     * Removing all stuff from the screen.
     * Adding GAME OVER label.
     * Adding results of the game.
     */
    private void gameOver() {
        removeAll();
        GLabel gameOver = new GLabel("GAME OVER");
        gameOver.setFont(new Font("Arial", Font.BOLD, getHeight() / 10));
        Color purpure = new Color(0x4C29AD);
        gameOver.setColor(purpure);
        add(gameOver, (getWidth() / 2) - (gameOver.getWidth() / 2), getHeight() / 2);
        int brickCount = NBRICKS_PER_ROW * NBRICK_ROWS - BRICK_COUNTER;

        GLabel stats = new GLabel("Yep, you lose.");
        stats.setFont(new Font("Arial", Font.BOLD, getHeight() / 15));
        Color red = new Color(0xAD2B44);
        stats.setColor(red);
        add(stats, (getWidth() / 2) - (stats.getWidth() / 2), (getHeight() / 2) + stats.getHeight());

        double procent = (brickCount * 100) / (NBRICKS_PER_ROW * NBRICK_ROWS);
        GLabel scoreLine = new GLabel("Your score: " + brickCount + " [" + procent + "%]");
        scoreLine.setFont(new Font("Arial", Font.BOLD, getHeight() / 20));
        Color green = new Color(0x5EAD44);
        scoreLine.setColor(green);
        add(scoreLine, (getWidth() / 2) - (scoreLine.getWidth() / 2), (getHeight() / 2) + gameOver.getHeight() + stats.getHeight());
    }

    /**
     * Game win method.
     * <p>
     * Removing all stuff from the screen.
     * Adding congratulation label.
     * Adding results of the game.
     */
    private void youWin() {
        removeAll();
        GLabel youWin = new GLabel("YOU WIN!");
        youWin.setFont(new Font("Arial", Font.BOLD, getHeight() / 10));
        Color green = new Color(0x5EAD44);
        youWin.setColor(green);
        add(youWin, (getWidth() / 2) - (youWin.getWidth() / 2), getHeight() / 2);
        int brickCount = NBRICKS_PER_ROW * NBRICK_ROWS - BRICK_COUNTER;

        double procent = (brickCount * 100) / (NBRICKS_PER_ROW * NBRICK_ROWS);
        GLabel scoreLine = new GLabel("Your score: " + brickCount + " [" + procent + "%]");
        scoreLine.setFont(new Font("Arial", Font.BOLD, getHeight() / 20));
        Color orange = new Color(0xEB8C21);
        scoreLine.setColor(orange);
        add(scoreLine, (getWidth() / 2) - (scoreLine.getWidth() / 2), (getHeight() / 2) + scoreLine.getHeight());
    }

    /**
     * Mouse move event.
     * Set paddle location the same as mouse position.
     *
     * @param event - mouse event.
     */
    public void mouseMoved(MouseEvent event) {
        if (!mouseOutOfScreen(event)) {
            paddle.setLocation(event.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET);
        }
    }

    /**
     * Mouse click event.
     * Set Y velocity by slider value.
     * @param event - mouse event.
     */
    public void mouseClicked(MouseEvent event) {
        if (vy == 0) {
            vy = diffSlider.getValue() * 1.5;
        }
    }

    /**
     * Mouse out of screen event.
     *
     * @param event - mouse event
     * @return boolean expression. True if mouse cursor in right zone on screen.
     */
    private boolean mouseOutOfScreen(MouseEvent event) {
        return ((event.getX() < PADDLE_WIDTH / 2) || (event.getX() > getWidth() - PADDLE_WIDTH / 2));
    }
}