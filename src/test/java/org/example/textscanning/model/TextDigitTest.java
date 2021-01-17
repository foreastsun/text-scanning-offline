package org.example.textscanning.model;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextDigitTest {

    @Test
    void testHashCodeAndEqualsWithNullNumber() {
        Map<TextDigit, String> m = new HashMap<>();
        TextDigit td = new TextDigit(null);
        m.put(td, "val1");
        assertEquals("val1", m.get(new TextDigit(null)));
    }

    @Test
    void testHashCodeAndEqualsWithZeroNumberEntry() {
        Map<TextDigit, String> m = new HashMap<>();
        TextDigit td = new TextDigit(new String[]{});
        m.put(td, "val1");
        assertEquals("val1", m.get(new TextDigit(new String[]{})));
    }

    @Test
    void testHashCodeAndEqualsWithNullNumberEntry() {
        Map<TextDigit, String> m = new HashMap<>();
        TextDigit td = new TextDigit(new String[]{" _ ", null, " | "});
        m.put(td, "val1");
        assertEquals("val1", m.get(new TextDigit(new String[]{" _ ", null, " | "})));
    }

    @Test
    void testHashCodeAndEqualsWithEmptyNumberEntry() {
        Map<TextDigit, String> m = new HashMap<>();
        TextDigit td = new TextDigit(new String[]{" _ ", " | ", ""});
        m.put(td, "val1");
        assertEquals("val1", m.get(new TextDigit(new String[]{" _ ", " | ", ""})));
    }

    @Test
    void testHashCodeAndEquals() {
        Map<TextDigit, String> m = new HashMap<>();
        TextDigit td = new TextDigit(new String[]{" _ ", "|_|", " _|"});
        m.put(td, "val1");
        assertEquals("val1", m.get(new TextDigit(new String[]{" _ ", "|_|", " _|"})));
    }

    @Test
    void testToString() {
        TextDigit td = new TextDigit(new String[] {
                " _ ",
                "| |",
                "|_|"
        });

        assertEquals("\n _ \n| |\n|_|\n", td.toString());
    }

    @Test
    void testNotEqual() {
        TextDigit td = new TextDigit(new String[] {
                " _ ",
                "| |",
                "|_|"
        });
        TextDigit td2 = new TextDigit(new String[] {
                " _ ",
                "|  ",
                "|_|"
        });

        assertNotEquals(td, td2);
    }

    @Test
    void testNotEqualWithNull() {
        TextDigit td = new TextDigit(new String[] {
                null,
                "| |",
                "|_|"
        });
        TextDigit td2 = new TextDigit(new String[] {
                " _ ",
                "| |",
                "|_|"
        });

        assertNotEquals(td, td2);
    }

    @Test
    void testNotEqualType() {
        TextDigit td = new TextDigit(new String[] {
                null,
                "| |",
                "|_|"
        });
        assertNotEquals(td, "dummy");
    }

    @Test
    void testEqualSelf() {
        TextDigit td = new TextDigit(new String[] {
                null,
                "| |",
                "|_|"
        });
        assertTrue(td.equals(td));
    }
}