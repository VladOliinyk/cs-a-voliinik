package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    /**
     * Main method.
     * Asks input data.
     * Calling exponentiation method.
     */
    public void run() {
        double base = readDouble("Enter the BASE number: ");
        int exponent = readInt("Enter the EXPONENT number: ");
        if ((base != 0) && (exponent > 0)) {
            double raisedNumber = raiseToPower(base, exponent);
            println(base + "^" + exponent + " = " + raisedNumber);
        } else {
            println("Input incorrect");
        }

    }


    /**
     * Exponentiation method.
     * <p>
     * Checks some condition.
     * Calling calculation method.
     *
     * @param base     Base of exponentiation.
     * @param exponent Exponent of exponentiation.
     * @return Raised number.
     */
    private double raiseToPower(double base, int exponent) {
        double result;

        if (exponent == 0) {
            return 1; // Based on the conditions ( x^0 = 1 )
        }
        if (base == 0) {
            return 0;
        }
        if (exponent > 0) {
            result = calculations(base, exponent);
        } else {
            exponent = -exponent;
            result = calculations(base, exponent);
            return (1 / result); // If exponent < 0 result value should be inverted.
        }

        return result;
    }


    /**
     * Calculation method.
     * Standard exponentiation. Base and exponent > 0. All right.
     * Raising the "base" in the degree of "exponent".
     *
     * @param base     Base of exponentiation.
     * @param exponent Exponent of exponentiation.
     * @return Result of calculations
     */
    private double calculations(double base, int exponent) {
        double calculationsResult = base;
        for (int i = 0; i < exponent - 1; i++) {
            calculationsResult = calculationsResult * base;
        }
        return calculationsResult;
    }
}