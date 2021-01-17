package org.example.textscanning.model;

import java.util.Arrays;

public class TextDigit {
    private String[] number;

    public TextDigit(String[] number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        if (number == null) return 0;

        int hashCode = 1;
        for (int i = 0; i < number.length; i++) {
            if (number[i] == null) hashCode *= i;
            else if (number[i].equals("")) hashCode *= i * 7;
            else hashCode *= number[i].hashCode();
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object anObject) {

        if (this == anObject) {
            return true;
        }
        if (anObject instanceof TextDigit) {
            TextDigit anotherNumber = (TextDigit) anObject;
            if (number == null && anotherNumber.number == null)
                return true;
            else if (number.length == anotherNumber.number.length) {
                for (int m = 0; m < number.length; m++) {
                    if (!equals(number[m], anotherNumber.number[m])) return false;
                }
                return true;
            }
        }
        return false;
    }

    private boolean equals(String a, String b) {
        if (a == null && b == null) return true;
        if (a != null) return a.equals(b);
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        Arrays.stream(number).forEach(e -> sb.append(e).append('\n'));
        return sb.toString();
    }
}
