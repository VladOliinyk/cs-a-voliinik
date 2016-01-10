package com.shpp.cs.namesurfer;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameSurferDataBase implements NameSurferConstants {

    ArrayList<String> dataBase = new ArrayList<String>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(filename));
            while (true) {
                String fileString = file.readLine();
                if (fileString == null) {
                    break;
                }
                dataBase.add(fileString);
            }
            file.close();
        } catch (IOException e) {
            //System.out.println("! Something wrong while opening file :c ");
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        for (int i = 0; i < dataBase.size(); i++) {
            NameSurferEntry entry = new NameSurferEntry(dataBase.get(i));
            if (entry.getName().toLowerCase().equals(name.toLowerCase())) {
                //System.out.println(entry.toString()); // output final string to console
                return entry;
            }
        }
        return null;
    }
}

