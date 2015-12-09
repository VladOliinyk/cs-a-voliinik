package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

// Another solving of Assignment3Part1 (without arrays)
public class Assignment3Part7 extends TextProgram {

    public static int DAYS = 7;
    // duration of training (in minutes)
    public static int CARDIOVASCULAR_TRAINING_TIME = 30;
    public static int LOW_BLOOD_PRESSURE_TIME = 40;

    public static int CARDIOVASCULAR_COUNTER = 0;
    public static int LOW_BLOOD_PRESSURE_COUNTER = 0;

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
     * In cycle asking data on each day.
     * Checking are user not kidding program. (If user training more than 24 hours per day it's not good)
     * Calling controller for duration of cardiovascular and low blood pressure training.
     */
    private void interview() {
        for (int i = 1; i <= DAYS; i++) {
            String inputString = readLine("How many minutes did you do on day " + i + "? ");
            if (inputIsCorret(inputString) && userNotKidding(inputString)) {
                int trainingDuration = Integer.valueOf(inputString);
                isCardiovascularTrainingCopmlete(trainingDuration);
                isLowBloodPressureTrainingComplete(trainingDuration);
            } else {
                i--;
            }
        }
    }

    /**
     * Cardiovascular training duration controller.
     * Checks during training for cardiovascular healthy.
     *
     * @param trainingDuration Training duration.
     */
    private void isCardiovascularTrainingCopmlete(int trainingDuration) {
        if (trainingDuration >= CARDIOVASCULAR_TRAINING_TIME) {
            CARDIOVASCULAR_COUNTER++;
        }
    }

    /**
     * Cardiovascular training duration controller.
     * Checks during training for low blood pressure.
     *
     * @param trainingDuration Training duration.
     */
    private void isLowBloodPressureTrainingComplete(int trainingDuration) {
        if (trainingDuration >= LOW_BLOOD_PRESSURE_TIME) {
            LOW_BLOOD_PRESSURE_COUNTER++;
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
     *
     * @return verdict string
     */
    private String SubjCardiovascularHealthy() { // 30+ minutes 5+ days
        if (CARDIOVASCULAR_COUNTER >= 5) {
            return "\tGreat job! You've done enough exercise for cardiovascular health.";
        } else {
            return ("\tYou needed to train hard for at least " + (5 - CARDIOVASCULAR_COUNTER) + " more day(s) a week!");
        }
    }

    /**
     * Blood PressureL Checker.
     *
     * @return verdict string
     */
    private String BloodPressureLow() { // 40+ minutes 3+ days

        if (LOW_BLOOD_PRESSURE_COUNTER >= 3) {
            return "\tGreat job! You've done enough exercise to keep a low blood pressure.";
        } else {
            return ("\tYou needed to train hard for at least " + (3 - LOW_BLOOD_PRESSURE_COUNTER) + " more day(s) a week!");
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
