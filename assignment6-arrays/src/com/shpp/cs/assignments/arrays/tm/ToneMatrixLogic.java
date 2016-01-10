package com.shpp.cs.assignments.arrays.tm;

import java.util.ArrayList;

import static acm.util.JTFTools.pause;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        boolean[] rowNumbers = new boolean[16];

        // определение включенных в текущей колонке
        for (int i = 0; i < 16; i++) {
            if (toneMatrix[i][column]) {
                rowNumbers[i] = true;
                // суммирование звуков
                for (int j = 0; j < result.length; j++) {
                    result[j] += samples[i][j];
                }
            }
        }

        // нормальизация волны [-1.0;1.0]
        for (int i = 0; i < result.length; i++) {
            if ((result[i] > 1.0) || (result[i] < -1.0)) {
                for (int j = 1; j < result.length; j++) {
                    result[j]/=result[i];
                }
            }
        }

        return result;
    }

}
