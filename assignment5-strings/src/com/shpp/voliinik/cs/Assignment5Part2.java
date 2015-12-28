package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number: ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {

        int length = getMaxLength(n1, n2) + 1;

        int[] numeric1 = createIntegerArray(n1, length);
        int[] numeric2 = createIntegerArray(n2, length);

        int[] sumUp = sumUpArrays(numeric1, numeric2);
        return integerToString(sumUp);

    }

    /**
     * Get max length of two input strings;
     *
     * @param n1 The first string.
     * @param n2 The second string.
     * @return A length of a larger string.
     */
    private int getMaxLength(String n1, String n2) {
        return n1.length() > n2.length() ? n1.length() : n2.length();
    }

    /**
     * Create integer array out a string.
     * NOTE: Method write numbers one by one
     * started not from 0 pos in array!
     * <p>
     * It is starts from the position
     * from which the last character
     * will be in the last cell of an array.
     *
     * @param string The string.
     * @param length The length of array we need to create.
     * @return An integer array.
     */
    private int[] createIntegerArray(String string, int length) {
        int[] integerArray = new int[length];

        int newLength = length - string.length();

        for (int i = 0; i < string.length(); i++) {
            integerArray[newLength + i] = string.charAt(i) - '0';
        }

        return integerArray;
    }

    /**
     * Summing up two integer arrays.
     *
     * @param firstArray  The first array.
     * @param secondArray The second array.
     * @return fixed array.
     */
    private int[] sumUpArrays(int[] firstArray, int[] secondArray) {
        int[] sum = new int[firstArray.length];

        for (int i = 0; i < firstArray.length; i++) {
            sum[i] = firstArray[i] + secondArray[i];
        }

        return fixArray(sum);
    }


    /**
     * Fixing the array.
     * NOTE: When we summing up cells one by one
     * we can "overflow" the result cell.
     * So we need to fix those cells.
     *
     * @param array The array which fixing.
     * @return A fixed array.
     */
    private int[] fixArray(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] > 9) {
                array[i] -= 10;
                array[i - 1]++;
            }
        }
        return array;
    }

    /**
     * Create string out an integer array.
     *
     * @param intArray The integer array.
     * @return A string.
     */
    private String integerToString(int[] intArray) {
        String string = "";
        if (intArray[0] != 0) {
            string += intArray[0];
        }
        for (int i = 1; i < intArray.length; i++) {
            string += intArray[i];
        }
        return string;
    }
}