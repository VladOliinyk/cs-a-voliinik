package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Assignment5Part3 extends TextProgram {

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
        println("Hello dude (or lady)!\n Please enter 'exit' if you want close the game.");

        while (true) {
            String numberPlate = getNumberPlate();
            if ((numberPlate.toLowerCase()).equals("exit")) {
                break;
            }
            ArrayList<Character> numberPlateLetters = getLettersFromNumberPlate(numberPlate);

            printArrayList(findTheWords(dictionary, numberPlateLetters), numberPlateLetters);
        }
        println("Thanks for \"Californication\"! Good buy! ");
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
    private ArrayList<Character> getLettersFromNumberPlate(String numberPlate) {
        ArrayList<Character> npLetters = new ArrayList<Character>();
        for (int i = 0; i < numberPlate.length(); i++) {
            if (isLetter(numberPlate.charAt(i))) {
                npLetters.add(numberPlate.charAt(i));
            }
        }
        return npLetters;
    }

    /**
     * Checks if input character is letter.
     *
     * @param ch Еру input haracterю
     * @return boolean expression.
     */
    private boolean isLetter(char ch) {
        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (char letter : letters) {
            if (ch == letter) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finding the words from dictionary by some letters.
     * Making the regular expression from letters which are looking for.
     * Finding matches.
     *
     * @param dictionary The dictionary where are looking for.
     * @param npl        The arrayList of letters which are looking for.
     * @return An arrayList of strings (found words).
     */
    private ArrayList<String> findTheWords(ArrayList<String> dictionary, ArrayList<Character> npl) {
        ArrayList<String> resultWords = new ArrayList<String>();

        String regExp = makeRegExp(npl);

        for (String word : dictionary) { // по запросу 'wash' - 120 слов
            if (Pattern.matches(regExp, word)) {
                resultWords.add(word);
            }
        }

        return resultWords;
    }

    /**
     * Make the regular expression from input letters.
     *
     * @param npl The input letters.
     * @return string of the regular expression.
     */
    private String makeRegExp(ArrayList<Character> npl) {
        String regExp = "^";

        for (char a : npl) {
            if (regExp.equals("^")) {
                regExp = regExp + "([a-z]*)+(" + a + "{1})";
            } else {
                regExp = regExp + "+([a-z]*)+(" + a + "{1})";
            }
        }
        regExp = regExp + "+([a-z]*)";

        return regExp;
    }

    /**
     * Print array list of found words.
     *
     * @param wordsArray The arrayList of found words.
     * @param npl        The letters which are looking for.
     */
    private void printArrayList(ArrayList<String> wordsArray, ArrayList<Character> npl) {
        if (wordsArray.size() != 0) {
            int count = wordsArray.size();
            print("   Here is your " + count + " words with ");
            for (int i = 0; i < npl.size(); i++) {
                print(" '" + npl.get(i) + "'");
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
