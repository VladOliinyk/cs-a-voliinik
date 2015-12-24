package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {
    public void run() {
        //doMyTests();

        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        if (word.length() > 0) {
            word = word.toLowerCase();
            int syllableCounter = 0;

            if (isVowel(word.charAt(0))) {  // для первого символа
                syllableCounter++;
            }

            for (int i = 1; i < word.length(); i++) {       // для всех символов кроме ПЕРВОГО
                if (isVowel(word.charAt(i))) {                  // если текущий символ гласный
                    if (!isVowel(word.charAt(i - 1))) {             // усли предыдущий - не гласный
                        syllableCounter++;                              // инкремент
                    }
                }
            }

            if ((syllableCounter != 1)&&(word.charAt(word.length()-1) == 'e')) {
                syllableCounter--;
            }

            return syllableCounter;
        } else { // when "word.lenght < 1"
            return 0;
        }
    }

    /**
     * Checking if a given character a vowel.
     *
     * @param ch - given character;
     * @return - true or false.
     */
    private boolean isVowel(char ch) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'y'};

        for (char letter : vowels) {
            if (ch == letter) {
                return true;
            }
        }
        return false;
    }

    /**
     * Making some tests of algorithm.
     * Calling corresponding method.
     */
    private void doMyTests() {
        println("- - - - - - - - - -");
        // tests for ш++ algorithm
        myTest("Me", 1);
        myTest("The", 1);
        myTest("She", 1);
        myTest("Nimble", 1);
        myTest("Growth", 1);
        myTest("Unite", 2);
        myTest("Quokka", 2);
        myTest("Syllable", 2);
        myTest("Springbok", 2);
        myTest("Unity", 3);
        myTest("Manatee", 2);
        myTest("Manatae", 2);
        myTest("Manatea", 3);
        myTest("Beautiful", 3);
        myTest("Possibilities", 5);
        myTest("Hello", 2);
        myTest("Hello quokka", 4);
        myTest("How", 1);
        myTest("Are", 1);
        myTest("You", 1);
        myTest("How you are", 3);
        myTest("Toy", 1);
        myTest("eee", 1);
        myTest("aaaeee", 1);
        myTest("base", 1);
        myTest("singlee", 1);
        myTest("Wallee", 1);
        myTest("Walle-E", 2);
        myTest("", 0);
        myTest(" ", 0);
        myTest("a", 1);
        myTest("It can be applied even to the proposals.", 12);
        myTest("But I can not make a \"simplified\" algorithm of the task. :c", 16);
        println("- - - - - - - - - -");
    }

    /**
     * Compares number of syllables in given word & predetermined number of syllables.
     * Calling main method.
     *
     * @param word - given word.
     * @param i    - number of syllables.
     */
    private void myTest(String word, int i) {
        if (!(syllablesIn(word) == i)) {
            print("! ");
        } else {
            print("  ");
        }
        println((syllablesIn(word) == i) + " "+syllablesIn(word) + " : [ " + word + " ]");
    }
}




