package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;
// OUT OF DATE
public class Assignment5Part1Universal extends TextProgram {
    public void run() {
        doMyTests();

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
        // only eng:
         char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'y'};
        // eng + ru + ua:
        //char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'y', 'а', 'у', 'о', 'ы', 'и', 'э', 'я', 'ю', 'ё', 'е', 'і', 'ї', 'є'};

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
        // tests for universal algorithm
        myTest("Me", 1);
        myTest("The", 1);
        myTest("She", 1);
        myTest("Nimble", 2);
        myTest("Growth", 1);
        myTest("Unite", 3);
        myTest("Quokka", 2);
        myTest("Syllable", 3);
        myTest("Springbok", 2);
        myTest("Unity", 3);
        myTest("Manatee", 3);
        myTest("Manatae", 3);
        myTest("Manatea", 3);
        myTest("Beautiful", 3);
        myTest("Possibilities", 5);
        myTest("Hello", 2);
        myTest("Hello quokka", 4);
        myTest("How", 1);
        myTest("Are", 2);
        myTest("You", 1);
        myTest("How you are", 4);
        myTest("Toy", 1);
        myTest("eee", 1);
        myTest("aaaeee", 1);
        myTest("base", 2);
        myTest("singlee", 2);
        myTest("Wallee", 2);
        myTest("Walle-E", 3);
        myTest("", 0);
        myTest(" ", 0);
        myTest("a", 1);
        myTest("Maybe", 2);
        myTest("it is", 2);
        myTest("universal", 4);
        myTest("method.", 2);
        myTest("It can be applied even to the proposals.", 12);
        myTest("But I can not make a \"simplified\" algorithm of the task. :c", 16);

        println("\n  If you have some troubles after that moment, you should check two sings: ");
        println("     1) Are you enable eng+ru+ua vowels array in \"isVowel\" method?");
        println("     2) Are you sure your OS supports cyrillic?\n");

        myTest("And also russian and ukrainian languages, наприклад:", 15);
        myTest("Привіт!", 2);
        myTest("Інфляція", 3);
        myTest("Це - українська!", 4);
        myTest("А вот это написанно на русском!", 11);
        myTest("И как Вы уже могли заметить, you can combine різні мови.", 19);

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




