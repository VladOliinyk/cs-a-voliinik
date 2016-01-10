package com.shpp.cs.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {

    private String name = "";
    private int[] part = new int[12];

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] parts = line.split(" ");
        name = parts[0];
        for (int i = 0; i < 12; i++) {
            part[i] = Integer.parseInt(parts[i + 1]);
        }
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        if ((decade >= 0) && (decade < 13)) {
            return part[decade];
        }
        return 0;
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        String result = name + " [";
        for (int i = 0; i < part.length; i++) {
            if (i != part.length - 1) {
                result += part[i] + " ";
            } else {
                result += part[i] + "]";
            }
        }
        return result;
    }
}

