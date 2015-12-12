package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;


public class Assignment3Part1 extends TextProgram {

    /**
     * Global array for 7 days training info.
     */
    public static int[] array = new int[7];

    public static int DAYS = 7;
    // duration of training (in minutes)
    public static final int CARDIOVASCULAR_TRAINING_TIME = 30;
    public static int LOW_BLOOD_PRESSURE_TIME = 40;
    /**
     * Main method.
     * Calling interview (data input) method.
     * Calling verdict (report) method.
     */
    public void run() {
        interview();
        verdict();
    }

    /**
     * Data input method.
     * <p>
     * In cycle writes data on each day.
     * Checking are user not kidding program. (If user training more than 24 hours per day it's not good)
     */
    private void interview() {
        for (int i = 1; i < 8; i++) {
            String inputString = readLine("How many minutes did you do on day " + i + "? ");
            if (inputIsCorret(inputString) && userNotKidding(inputString)) {
                array[i-1] = Integer.valueOf(inputString);
            } else {
                i--;
            }
        }
    }

    /**
     * Verdict method.
     * <p>
     * Calling method, which checking is there enough training to maintain cardiovascular health.
     * Calling method, which checking is there enough training to keep a low blood pressure.
     */

    private void verdict() {
        println("Cardiovascular health:");
        println(SubjCardiovascularHealthy());

        println("Blood pressure:");
        println(BloodPressureLow());
    }

    /**
     * Cardiovascular Health Checker.
     * <p>
     * In cycle calculate, how many days user trained enough to maintain cardiovascular health.
     *
     * @return verdict string
     */
    private String SubjCardiovascularHealthy() { // 30+ minutes 5+ days
        int dailyChallenge = 0;
        for (int i = 0; i < DAYS; i++) {
            if (array[i] >= CARDIOVASCULAR_TRAINING_TIME) {
                dailyChallenge++;
            }
        }
        if (dailyChallenge >= 5) {
            return "\tGreat job! You've done enough exercise for cardiovascular health.";
        } else {
            return ("\tYou needed to train hard for at least " + (5 - dailyChallenge) + " more day(s) a week!");
        }
    }

    /**
     * Blood PressureL Checker.
     * <p>
     * In cycle calculate, how many days user trained enough to keep a low blood pressure.
     *
     * @return verdict string
     */
    private String BloodPressureLow() { // 40+ minutes 3+ days
        int dailyChallenge = 0;
        for (int i = 0; i < DAYS; i++) {
            if (array[i] >= LOW_BLOOD_PRESSURE_TIME) {
                dailyChallenge++;
            }
        }
        if (dailyChallenge >= 3) {
            return "\tGreat job! You've done enough exercise to keep a low blood pressure.";
        } else {
            return ("\tYou needed to train hard for at least " + (3 - dailyChallenge) + " more day(s) a week!");
        }
    }

    /**
     * Checking is inout is correct.
     *
     * @param inputString Input string.
     * @return boolean answer.
     */
    public boolean inputIsCorret(String inputString) {
        if (inputString.isEmpty()) {
            println("\t Please enter this:");
            return false;
        }
        for (int i = 0; i < inputString.length(); i++) {
            if (!Character.isDigit(inputString.charAt(i))) {
                println("\t Hmm.. Are you sure you enter a integer positive number?");
                return false;
            }
        }
        return true;
    }

    /**
     * Checking is inout is correct in value.
     *
     * @param inputString Input string.
     * @return boolean answer.
     */
    public boolean userNotKidding(String inputString) {
        if (Integer.valueOf(inputString) > 1440) { // trained minutes bigger than minutes in 1 day
            println("\t Hmm.. Are you sure you training hard more than 24 hours per day?");
            return false;
        }
        return true;
    }
}
