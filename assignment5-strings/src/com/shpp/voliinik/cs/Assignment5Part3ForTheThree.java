package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part3ForTheThree extends TextProgram {

    /**
     * Path to dictionary file.
     */
    private static final String DICTIONARY_FILE = "en-dictionary.txt";

    /**
     * Asks user the numberplate.
     * Print array of words that can be formed from the letters of the number plate.
     */
    public void run() {
        ArrayList<String> dictionary = readFile();
        if (dictionary != null) {
            println("Hello dude (or lady)!\n Please enter 'exit' if you want close the game.");

            boolean wantToExit = false;
            while (true) {
                String numberPlate = getNumberPlate().toLowerCase();
                if ((numberPlate.toLowerCase()).equals("exit")) {
                    break;
                }
                char[] numberPlateLetters = getLettersFromNumberPlate(numberPlate);
                if (numberPlateLetters != null) {
                    printArrayList(findTheWords(dictionary, numberPlateLetters), numberPlateLetters);
                } else {
                    println("Are you sure you enter right numberplate?");
                }
            }
        } else {
            println("! Open file error!");
        }
    }

    /**
     * Reading file.
     *
     * @return An arrayList of words from file.
     */
    private ArrayList<String> readFile() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(DICTIONARY_FILE));
            while (true) {
                String word = file.readLine();
                if (word == null) {
                    break;
                }
                result.add(word);
            }
            file.close();
        } catch (IOException e) {
            println("! Something wrong :c ");
            return null;
        }
        return result;
    }

    /**
     * Getting the numberplate.
     *
     * @return A numberplate.
     */
    public String getNumberPlate() {
        return readLine("  Please enter the number plate: ");
    }

    /**
     * Getting letters from numberplate.
     *
     * @param numberPlate The numberplate from which getting letters.
     * @return An arrayList of character.
     */
    private char[] getLettersFromNumberPlate(String numberPlate) {
        numberPlate = lettersIn(numberPlate);
        if (numberPlate.length() != 3) {
            return null;
        } else {
            char[] numberPlateLetters = new char[3];
            for (int i = 0; i < numberPlate.length(); i++) {
                if (isLetter(numberPlate.charAt(i))) {
                    numberPlateLetters[i] = numberPlate.charAt(i);
                }
            }
            return numberPlateLetters;
        }
    }

    /**
     * Given a string, returns a new string consisting of all of the
     * letters that appear in the original string in the order in which
     * they appear.
     *
     * @param text The input string.
     * @return The letters of that string in the order in which they appear.
     */
    private String lettersIn(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            /* Test if the current character is a letter. If so, add it. */
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                result += ch;
            }
        }
        return result;
    }

    /**
     * Checks if input character is letter.
     *
     * @param ch Еру input haracterю
     * @return boolean expression.
     */
    private boolean isLetter(char ch) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String myChar = "" + ch;
        return letters.contains(myChar);
    }

    /**
     * Finding the words from dictionary by some letters.
     * Making the regular expression from letters which are looking for.
     * Finding matches.
     *
     * @param dictionary         The dictionary where are looking for.
     * @param numberPlateLetters The arrayList of letters which are looking for.
     * @return An arrayList of strings (found words).
     */
    private ArrayList<String> findTheWords(ArrayList<String> dictionary, char[] numberPlateLetters) {
        ArrayList<String> resultWords = new ArrayList<String>();

        for (String word : dictionary) {
            int letterPos = 0;

            letterPos = word.indexOf(numberPlateLetters[0], letterPos+1);
            if (letterPos != -1) {
                letterPos = word.indexOf(numberPlateLetters[1], letterPos+1);
                if (letterPos != -1) {
                    letterPos = word.indexOf(numberPlateLetters[2], letterPos+1);
                    if (letterPos != -1) {
                        resultWords.add(word);
                    }
                }
            }
        }

        return resultWords;
    }


    /**
     * Print array list of found words.
     *
     * @param wordsArray         The arrayList of found words.
     * @param numberPlateLetters The array of letters from numberplate.
     */
    private void printArrayList(ArrayList<String> wordsArray, char[] numberPlateLetters) {
        if (wordsArray.size() != 0) {
            int count = wordsArray.size();
            print("   Here is your " + count + " words with ");
            for (int i = 0; i < 3; i++) {
                print(" '" + numberPlateLetters[i] + "'");
            }
            print(" letters\n");
            for (int i = 0; i < wordsArray.size(); i++) {
                println("    - " + wordsArray.get(i));
            }
        } else {
            println("   It seems that then no words...");
        }
    }
}
