package org.example.textscanning.bean;

import org.example.textscanning.model.TextDigit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TextDigitMapTest {
    TextDigitMap map;

    @BeforeEach
    void setup() throws Exception {
        map = new TextDigitMap();
        Whitebox.setInternalState(map, "numberTemplatePath", "input\\number-template");
        Whitebox.setInternalState(map, "textNumberParser", new TextNumberParser());
        Whitebox.invokeMethod(map, "init");
    }

    @Test
    void getDigit() throws Exception {

        assertNull(map.getDigit(new TextDigit(null)));
        assertNull(map.getDigit(new TextDigit(new String[]{})));
        assertNull(map.getDigit(new TextDigit(new String[]{"  ", "  |", "   "})));
        assertEquals(Character.valueOf('1'), map.getDigit(new TextDigit(new String[]{"   ", "  |", "  |"})));

    }

    @Test
    void getNumberString() {
        assertEquals("123", map.getNumberString(
                Arrays.asList(
                        new TextDigit(new String[]{"   ",
                                                   "  |",
                                                   "  |"}),
                        new TextDigit(new String[]{" _ ",
                                                   " _|",
                                                   "|_ "}),
                        new TextDigit(new String[]{" _ ",
                                                   " _|",
                                                   " _|"})
                )));
    }

    @Test
    void getNumberStringWithILL() {
        assertEquals("1?3ILL", map.getNumberString(
                Arrays.asList(
                        new TextDigit(new String[]{"   ",
                                "  |",
                                "  |"}),
                        new TextDigit(new String[]{" _ ",
                                " _|",
                                "|_|"}),
                        new TextDigit(new String[]{" _ ",
                                " _|",
                                " _|"})
                )));
    }
}