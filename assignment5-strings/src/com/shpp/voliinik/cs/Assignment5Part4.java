package com.shpp.voliinik.cs;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    /**
     * Path to dictionary file.
     */
    private static final String FILENAME = "filename.csv";

    /**
     * Calling extractColumn method.
     */
    public void run() {
        extractColumn(FILENAME, 1);
    }

    private void extractColumn(String filename, int columnIndex) {
        ArrayList<String> fileStrings = readFile(filename);
        ArrayList<ArrayList> tableRows = new ArrayList<ArrayList>();

        for (String string : fileStrings) {
            ArrayList<String> tableCell = getCellsFromString(string);
            tableRows.add(tableCell);
        }

        printResult(tableRows, columnIndex);
    }


    /**
     * Reading file.
     *
     * @return An arrayList of words from file.
     */
    private ArrayList<String> readFile(String filename) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            while (true) {
                String string = file.readLine();
                if (string == null) {
                    break;
                }
                result.add(string + ',');
            }
            file.close();
        } catch (IOException e) {
            println("! Something wrong :c ");
        }
        return result;
    }

    /**
     * Getting cells from input string.
     *
     * @param string The string such as "word,word,word,"
     * @return An arrayList of words.
     */
    public ArrayList<String> getCellsFromString(String string) {
        ArrayList<String> cellsFromString = new ArrayList<String>();

        String newCell = "";
        char breakChar = ',';
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);


            if ((ch != breakChar) || (ch != ',')) {
                newCell += ch;
            } else {
                cellsFromString.add(newCell);
                newCell = "";
            }

            if (ch == '"') {
                if (breakChar == ',') {
                    breakChar = '"';
                } else {
                    breakChar = ',';
                }
            }
        }
        return cellsFromString;
    }

    /**
     * Print result string.
     *
     * @param tableRows   The arrayList of arrayLists of words from file.
     * @param columnIndex The index of column which need to print.
     */
    private void printResult(ArrayList<ArrayList> tableRows, int columnIndex) {
        print("[");
        for (int i = 0; i < tableRows.size(); i++) {
            ArrayList<String> row = tableRows.get(i);
            if (row.get(columnIndex).charAt(0) != '"') {
                print('"');
                print(row.get(columnIndex));
                print('"');
            } else {
                print(row.get(columnIndex));
            }
            if (i == tableRows.size() - 1) {
                print("]");
            } else {
                print(", ");
            }
        }
    }
}
