package com.shpp.cs.assignments.arrays.sg;

import acm.graphics.*;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] image = source.getPixelArray();

        boolean[][] result = new boolean[image.length][image[0].length];


        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                result[i][j] = ((GImage.getRed(image[i][j]) % 2) != 0);
            }
        }

        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p>
     * You can assume that the dimensions of the message and the image are the same.
     * <p>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] image = source.getPixelArray();
        int[][] secretImageArray = new int[image.length][image[0].length];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (message[i][j]) {
                    //сделать красный канал нечетным
                    secretImageArray[i][j] = redToEven(image[i][j]);
                } else {
                    //сделать красный канал четным
                    secretImageArray[i][j] = redToOdd(image[i][j]);
                }
            }
        }

        return new GImage(secretImageArray);
    }

    private static int redToOdd(int pixel) { // в ЧЕТНЫЙ
        int oldRed = GImage.getRed(pixel);
        int newRed;

        if (oldRed % 2 == 0) { // четный
            newRed = oldRed;
        } else { // если нечетный
            newRed = oldRed - 1;
        }

        return GImage.createRGBPixel(
                newRed,
                GImage.getGreen(pixel),
                GImage.getBlue(pixel),
                GImage.getAlpha(pixel));
    }

    private static int redToEven(int pixel) { // в НЕЧЕТНЫЙ
        int oldRed = GImage.getRed(pixel);
        int newRed;

        if (oldRed % 2 == 1) { //нечетный
            newRed = oldRed;
        } else { // если четный
            newRed = oldRed + 1;
        }

        return GImage.createRGBPixel(
                newRed,
                GImage.getGreen(pixel),
                GImage.getBlue(pixel),
                GImage.getAlpha(pixel));
    }
}
