package com.shpp.voliinik.cs;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class BreakoutExt extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 550;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 5;

    /**
     * Radius of the ball in pixels
     */
    private static int BALL_RADIUS = 8;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 60;

    /**
     * Width of a brick
     */
    private static int BRICK_WIDTH;

    /**
     * Height of a brick
     */
    private static int BRICK_HEIGHT;

    /**
     * Number of turns
     */
    private static int NTURNS = 5;

    /**
     * Ball and paddle global objects.
     */
    GRect paddle;
    GOval ball;

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
    JLabel lifeBoard = new JLabel("" + LIFE);

    /**
     * Brick counter.
     */
    private int BRICK_COUNTER = NBRICK_ROWS * NBRICKS_PER_ROW;
    JLabel scoreBoard = new JLabel("0");

    /**
     * Difficulty slider.
     */
    JSlider diffSlider = new JSlider(10, 30, 15);

    /**
     * X and Y velocity
     */
    private double vx = diffSlider.getValue() / 4, vy = 0;

    /**
     * Global data storage for pause.
     */
    private double pauseX, pauseY;
    private boolean isPause = false;

    /**
     * Buttons to the menu.
     */
    JButton addRowButton = new JButton(" Add row ");
    JButton addColButton = new JButton(" Add col ");
    JButton removeRowButton = new JButton(" Remove row ");
    JButton removeColButton = new JButton(" Remove col ");
    JButton startButton = new JButton("Start");

    /**
     * Variables for start settings screen.
     */
    GLabel rowsCount = new GLabel("");
    GLabel colsCount = new GLabel("");
    GLabel rowsTitle = new GLabel("Columns: ");
    GLabel colsTitle = new GLabel("Rows: ");
    Color title = new Color(0x0D5081);
    Color count = new Color(0x1F6A81);

    /**
     * Boolean switchers.
     */
    boolean gameIsReady = false;
    boolean gameStarted = false;

    /**
     * Pause data stuff.
     */
    GRect pauseScreen;
    GLabel pauseTitle;

    /**
     * Init method.
     * Adding slider on the top of the screen.
     */
    public void init() {
        add(new JLabel("Difficulty:"), NORTH);
        add(diffSlider, NORTH);
        add(startButton, NORTH);

        add(new JLabel("Life: "), NORTH);
        add(lifeBoard, NORTH);

        add(new JLabel("Score: "), NORTH);
        add(scoreBoard, NORTH);


        add(addRowButton, SOUTH);
        add(removeRowButton, SOUTH);
        add(addColButton, SOUTH);
        add(removeColButton, SOUTH);

        addActionListeners();
    }

    /**
     * Processor of start screen events.
     *
     * @param event button click event.
     */
    public void actionPerformed(ActionEvent event) {
        if (!gameStarted) {
            if (event.getSource() == startButton) {
                gameIsReady = true;
                BRICK_COUNTER = NBRICK_ROWS * NBRICKS_PER_ROW;
                scoreBoard.setText("" + (NBRICK_ROWS * NBRICKS_PER_ROW - BRICK_COUNTER));
                gameStarted = true;
            }
            if (event.getSource() == addRowButton) {
                if (NBRICK_ROWS < 15) {
                    NBRICK_ROWS++;
                }
            }
            if (event.getSource() == addColButton) {
                if (NBRICKS_PER_ROW < 50) {
                    NBRICKS_PER_ROW++;
                }
            }
            if (event.getSource() == removeRowButton) {
                if (NBRICK_ROWS > 1) {
                    NBRICK_ROWS--;
                }
            }
            if (event.getSource() == removeColButton) {
                if (NBRICKS_PER_ROW > 1) {
                    NBRICKS_PER_ROW--;
                }
            }
        }
    }

    /**
     * Main method.
     * Creating start settings screen.
     * Starting game.
     */
    public void run() {
        while (!gameIsReady) {
            createStartSettingsScreen();
            pause(100);
        }
        if (gameIsReady) {
            startGame();
        }
    }

    /**
     * Creating start settings screen.
     * Calling create menu method.
     * Calling method which update counters for row & column numbers.
     */
    private void createStartSettingsScreen() {
        createMenuScreen();
        createHowToPlayManual();
        remakeCounters();
    }

    /**
     * Creating menu screen.
     * Adding title to rows and cols counters.
     */
    private void createMenuScreen() {
        rowsTitle.setFont(new Font("Arial", Font.BOLD, getHeight() / 20));
        rowsTitle.setColor(title);
        colsTitle.setFont(new Font("Arial", Font.BOLD, getHeight() / 20));
        colsTitle.setColor(title);

        add(rowsTitle, (getWidth() / 2) - (rowsTitle.getWidth()), (getHeight() / 2) - rowsTitle.getHeight());
        add(colsTitle, (getWidth() / 2) - (colsTitle.getWidth()), (getHeight() / 2) + colsTitle.getHeight());

    }

    /**
     * Creating how to play manual on start settings screen.
     */
    private void createHowToPlayManual() {
        GLabel line1 = new GLabel("Press SpaceBar to launch ball.");
        GLabel line2 = new GLabel("Press P to pause game.");

        line1.setFont(new Font("Arial", Font.BOLD, 15));
        line2.setFont(new Font("Arial", Font.BOLD, 15));
        Color green = new Color(0xAD4E6C);
        line1.setColor(green);
        line2.setColor(green);

        add(line1, getWidth() / 2 - line1.getWidth() / 2, getHeight() - (getHeight() / 5) - 20);
        add(line2, getWidth() / 2 - line2.getWidth() / 2, getHeight() - getHeight() / 5);
    }

    /**
     * Remaking counters.
     * Update counters for row & column numbers.
     */
    private void remakeCounters() {
        remove(rowsCount);
        remove(colsCount);

        rowsCount = new GLabel("" + NBRICKS_PER_ROW);
        rowsCount.setFont(new Font("Arial", Font.BOLD, getHeight() / 20));
        rowsCount.setColor(count);

        colsCount = new GLabel("" + NBRICK_ROWS);
        colsCount.setFont(new Font("Arial", Font.BOLD, getHeight() / 20));
        colsCount.setColor(count);


        add(rowsCount, (getWidth() / 2) + (rowsCount.getWidth()), (getHeight() / 2) - rowsTitle.getHeight());
        add(colsCount, (getWidth() / 2) + (colsCount.getWidth()), (getHeight() / 2) + colsTitle.getHeight());
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
    public void startGame() {
        removeAllStuff();

        addMouseListeners();
        addKeyListeners();
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
     * Removing all objects from game screen.
     * Calling removing func.
     * Disabling all bottoms.
     */
    private void removeAllStuff() {
        removeAll();
        startButton.setEnabled(false);
        addRowButton.setEnabled(false);
        removeRowButton.setEnabled(false);
        addColButton.setEnabled(false);
        removeColButton.setEnabled(false);
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
        BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
        BRICK_HEIGHT = (int) (HEIGHT / 3 - BALL_RADIUS * 0.2 - BRICK_Y_OFFSET) / NBRICK_ROWS;

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
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(-1.0, 1.0);
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
        if (ballHitUpDownWalls()) {
            vy *= -1;
        }

        double x, y;

        /*
        (2)
        Here we have for which makes bypassing every point of the circle.
        And if found some colliding object behind circle do the "ricochet" from that object.
         */
        for (int i = 360; i > 0; i--) {
            x = (ball.getX() + BALL_RADIUS) + Math.cos(i) * (BALL_RADIUS + 0.1);
            y = (ball.getY() + BALL_RADIUS) + Math.sin(i) * (BALL_RADIUS + 0.1);

            collider = getElementAt(x, y);
            if (collider != null) {
                ricochet(collider);
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
        if (ball.getX() <= 1) {
            ball.setLocation(1, ball.getY());
            return true;
        }
        if ((ball.getX() + BALL_RADIUS * 2) >= getWidth()) {
            ball.setLocation(getWidth() - BALL_RADIUS * 2, ball.getY());
            return true;
        }

        return false;
    }

    /**
     * Checking ball position.
     * If ball hit the flore calling Round Lose method.
     *
     * @return true or false depending from ball position. True if ball hit the ceiling.
     */
    private boolean ballHitUpDownWalls() {
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
        lifeBoard.setText("" + LIFE);
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
            if (((ball.getY() + BALL_RADIUS * 0.8) < paddle.getY())
                    || (ball.getY() < paddle.getY())
                    ) {
                ricochetFromPaddle();
            }
        }
        if ((collider != paddle)
                && (collider != null)
                && (collider != ball)
                && (collider != pauseScreen)
                && (collider != pauseTitle)) {
            ricochetFromBrick(collider);
            remove(collider);
            BRICK_COUNTER--;
            scoreBoard.setText("" + (NBRICK_ROWS * NBRICKS_PER_ROW - BRICK_COUNTER));
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

        vy = diffSlider.getValue() / 2;
        vy *= -1;
        if (ballXCenter > (paddleX + ((paddle.getWidth() / 10) * 9))) {
            if (vx < 0) {
                vx *= -1 * rgen.nextDouble(0.8, 1.2);
            }
        } else if (ballXCenter > (paddleX + ((paddle.getWidth() / 3) * 2))) {
            if (vx > 0) {
                vx = (diffSlider.getValue() / 4) * rgen.nextDouble(1.2, 2.2);
            } else {
                vx = (diffSlider.getValue() / 4) * -rgen.nextDouble(0.8, 1.8);
            }
        } else if (ballXCenter > (paddleX + ((paddle.getWidth() / 3)))) {
            if (vx > 0) {
                vx = (diffSlider.getValue() / 4);
            } else {
                vx = -(diffSlider.getValue() / 4);
            }
        } else if (ballXCenter > (paddleX + (paddle.getWidth() / 10))) {
            if (vx > 0) {
                vx = (diffSlider.getValue() / 4) * rgen.nextDouble(0.8, 1.8);
            } else {
                vx = (diffSlider.getValue() / 4) * -rgen.nextDouble(1.2, 2.2);
            }
        } else {
            if (vx > 0) {
                vx *= -1 * rgen.nextDouble(0.8, 1.2);
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

        if (ballXcenter + (BALL_RADIUS / 2) < collider.getX()) {
            vx *= -1;
        }
        if (ballXcenter - (BALL_RADIUS / 2) > collider.getX() + BRICK_WIDTH) {
            vx *= -1;
        }

        if (ballYcenter + (BALL_RADIUS / 2) > collider.getY() + BRICK_HEIGHT) {
            vy *= -1;
        }
        if (ballYcenter - (BALL_RADIUS / 2) < collider.getY()) {
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
        if (!mouseOutOfRightZone(event) && paddle != null) {
            paddle.setLocation(event.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET);
        }
    }

    /**
     * Pause game method.
     * If game not paused yet:
     * saving X and Y ball velocity;
     * setting ball velocity to 0;
     * adding pause screen.
     */
    private void pauseGame() {
        if (!isPause) {
            pauseScreen = new GRect(getWidth() + 10, getHeight() + 10);
            Color pause = new Color(0, 0, 0, 195);
            pauseScreen.setFilled(true);
            pauseScreen.setFillColor(pause);

            pauseTitle = new GLabel("PAUSE");
            pauseTitle.setFont(new Font("Arial", Font.BOLD, getHeight() / 10));
            Color pauseTextColor = new Color(175, 75, 75);
            pauseTitle.setColor(pauseTextColor);

            add(pauseScreen, -5, -5);
            add(pauseTitle, (getWidth() / 2) - (pauseTitle.getWidth() / 2), getHeight() / 2);

            isPause = true;
            pauseX = vx;
            vx = 0;
            pauseY = vy;
            vy = 0;
        }
    }

    /**
     * Resume game method.
     * If game paused yet:
     * removing pause screen;
     * setting ball velocity from global storage.
     */
    private void resumeGame() {
        if (isPause) {
            remove(pauseScreen);
            remove(pauseTitle);
            isPause = false;
            vx = pauseX;
            vy = pauseY;
        }
    }

    /**
     * Key typed event.
     *
     * @param event key type event.
     */
    public void keyTyped(KeyEvent event) {
        RandomGenerator rgen = RandomGenerator.getInstance();
        println(event.getKeyCode());
        if (event.getKeyChar() == ' ' || event.getKeyChar() == 's') {
            if (vy == 0) {
                vy = diffSlider.getValue() / 2;
            }
            if (isPause) {
                resumeGame();
            }
        }
        if (event.getKeyChar() == 'p') {
            if (!isPause) {
                pauseGame();
            }
        }
    }

    /**
     * Mouse out of screen event.
     *
     * @param event - mouse event
     * @return boolean expression. True if mouse cursor in right zone on screen.
     */
    private boolean mouseOutOfRightZone(MouseEvent event) {
        return ((event.getX() < PADDLE_WIDTH / 2) || (event.getX() > getWidth() - PADDLE_WIDTH / 2));
    }
}