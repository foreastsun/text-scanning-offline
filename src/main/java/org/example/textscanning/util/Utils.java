package org.example.textscanning.util;

public class Utils {

    public static String toString(String[] lines) {
        if (lines == null) return "null";

        return String.join("\n", lines);

    }

}
