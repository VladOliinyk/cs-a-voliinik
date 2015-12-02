package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {

    /**
     * declaring a global variable
     *
     * Possible to implement the program without declaring global variables.
     * It is enough to pass the required parameters to the function.
     *
     * The output results can be displayed by calling println() directly in the decision.
     */
    public double aValue;
    public double bValue;
    public double cValue;
    public String result = "";

    /**
     * main method
     * calling solving method
     * displaying result string after solving
     */
    public void run() {
        greeting();
        solveQuadraticEquation();
        output(result);
    }

    /**
     *  calling functions for data input & for solving a quadratic equation
     */
    private void solveQuadraticEquation() {
        dataInput();
        calculation();
    }

    /**
     * data input method
     *
     * reading values from keyboard
     * assigning values to global variables
     */
    private void dataInput() {
        aValue = readDouble("Please enter a: ");
        bValue = readDouble("Please enter b: ");
        cValue = readDouble("Please enter c: ");
    }

    /**
     * pre calculation method
     *
     * checking discriminant & calling solving method if needed
     * if discriminant < 0 there are no real roots then
     * writing a corresponding message to the output string
     */
    private void calculation() {
        double discriminant = calcDiscriminant();

        if (discriminant < 0) {
            result = "There are no real roots";
        } else {
            calculateRoots(discriminant);
        }
    }

    /**
     * main calculation method
     * checking discriminant
     * choosing solution's method
     *
     * @param D - discriminant
     */
    private void calculateRoots(double D) {
        if ( D == 0 ) {
            double root = roundNumber(-(bValue/2*aValue));
            result = "There is one root: "+root;
        } else {
            double firstRoot =  ( ( (-bValue) + Math.sqrt(D) ) / (2*aValue) );
            double secondRoot = ( ( (-bValue) - Math.sqrt(D) ) / (2*aValue) );

            firstRoot = roundNumber(firstRoot);
            secondRoot = roundNumber(secondRoot);

            result = "There are two roots: "+firstRoot+" and "+secondRoot+" ";
        }
    }

    /**
     * rounding input parameter to 3 decimals
     *
     * about Math.rint(): http://www.tutorialspoint.com/java/number_rint.htm
     *
     * @param number
     * @return rounded number
     */
    private double roundNumber(double number) {
        return (Math.rint(1000.0 * number) / 1000.0);
    }

    /**
     * calculating discriminant
     *
     * @return Discriminant of a quadratic equation
     */
    private double calcDiscriminant() {
        return (bValue*bValue)-(4*aValue*cValue);
    }

    /**
     * Output result string.
     * 'result' string fills in calculateRoots()
     * @param result
     */
    private void output(String result) {
        println(result);
    }

    /**
     * greeting method
     */
    private void greeting() {
        println("Hello, welcome to \"Quadratic Equation Calculator 2000\"");
    }
}