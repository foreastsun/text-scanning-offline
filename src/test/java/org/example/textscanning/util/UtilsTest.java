package org.example.textscanning.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testToString() {
        assertEquals("null", Utils.toString(null));
        assertEquals("123\n456", Utils.toString(new String[]{"123", "456"}));
        assertEquals("", Utils.toString(new String[]{}));
    }
}