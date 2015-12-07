package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {

    int number = 1;

    public void run() {
        dataInput();
        numbersHailstone();
    }

    private void numbersHailstone() {
        while (number != 1) {
            print(number + " is ");

            if (number % 2 == 1) {
                print("odd so I make 3*n + 1: ");
                number = (3 * number) + 1;
                print(number + "\n");
            } else {
                print("even so I take half: ");
                number = number / 2;
                print(number + "\n");
            }

        }
    }

    private void dataInput() {
        for (int i = 1; i < 2; i++) {
            String inputString = readLine("Enter a number: ");
            if (inputIsCorret(inputString)) {
                number = Integer.valueOf(inputString);
            } else {
                i--;
            }
        }
    }

    public boolean inputIsCorret(String inputString) {
        if (inputString.isEmpty()) {
            return false;
        }
        for (int i = 0; i < inputString.length(); i++) {
            if (!Character.isDigit(inputString.charAt(i))) {
                println("\t Hmm.. Are you sure you enter a integer positive number?");
                return false;
            }
        }
        return true;
    }
}