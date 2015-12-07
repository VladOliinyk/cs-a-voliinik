package com.shpp.voliinik.cs;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part5 extends TextProgram {

    /** Declaring random generator. */
    RandomGenerator rgen = RandomGenerator.getInstance();

    /**
     * Main method.
     * Calling game method.
     */
    public void run() {
        letsPlay();
    }

    /**
     * Game method.
     * Calling ROLL method
     *      for playing while total winnings < 20$;
     */
    private void letsPlay() {
        int counter = 0;
        int win = 0;
        int total = 0;

        while (total < 20) {
            win = letsRoll();
            println("This game, you earned $" + win);
            total += win;
            println("Your total is $" + total);
            counter++;
        }
        println("It took " + counter + " games to earn >$20");
    }

    /** ROLL method.
     * Calculating winnings by coin toss.
     * @return winnings.
     */
    private int letsRoll() {
        int win = 1;
        while (rgen.nextBoolean()) {
            win += win;
        }
        return win;
    }
}