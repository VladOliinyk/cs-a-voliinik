package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    /**
     * Global variable.
     */
    public double result = 0;

    /**
     * Main method.
     * Asks input data.
     * Calling exponentiation method.
     */
    public void run() {
        double base = readDouble("Enter the BASE number: ");
        int exponent = readInt("Enter the EXPONENT number: ");
        raiseToPower(base, exponent);
        println(base + "^" + exponent + " = " + result);
    }

    /**
     * Exponentiation method.
     * <p>
     * Checks some condition.
     * Calling calculation method.
     *
     * @param base     Base of exponentiation.
     * @param exponent Exponent of exponentiation.
     */
    private void raiseToPower(double base, int exponent) {
        result = base;
        if (exponent == 0) {
            result = 1; // Based on the conditions ( x^0 = 1 )
        }
        if (exponent > 0) {
            calculations(base, exponent);
        } else {
            exponent = -exponent;
            calculations(base, exponent);
            result = 1 / result; // If exponent < 0 result value should be inverted.
        }
    }

    /**
     * Calculation method.
     * Standard exponentiation. Base and exponent > 0. All right.
     * Raising the "base" in the degree of "exponent".
     *
     * @param base     Base of exponentiation.
     * @param exponent Exponent of exponentiation.
     */
    private void calculations(double base, int exponent) {
        for (int i = 0; i < exponent - 1; i++) {
            result = result * base;
        }
    }
}