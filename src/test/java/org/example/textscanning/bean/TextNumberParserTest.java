package org.example.textscanning.bean;

import org.example.textscanning.exception.InvalidNumberException;
import org.example.textscanning.model.TextDigit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextNumberParserTest {

    @Test
    void parse() throws InvalidNumberException {

        TextNumberParser parser = new TextNumberParser();
        List<TextDigit> textDigits = parser.parse(new String[]{
                "    _  _     _  _  _  _  _ ",
                "  | _| _||_||_ |_   ||_||_|",
                "  ||_  _|  | _||_|  ||_| _|"
        });

        assertEquals(new TextDigit(new String[]{"   ", "  |", "  |"}), textDigits.get(0));
        assertEquals(new TextDigit(new String[]{" _ ", " _|", "|_ "}), textDigits.get(1));
    }

    @Test
    void parseWithInvalidLength() {
        Exception exception = assertThrows(InvalidNumberException.class, () ->
        {
            TextNumberParser parser = new TextNumberParser();
            List<TextDigit> textDigits = parser.parse(new String[]{
                    "    _  _     _  _  _  _  _",
                    "  | _| _||_||_ |_   ||_||_|",
                    "  ||_  _|  | _||_|  ||_| _|"
            });
        });

        assertTrue(exception.getMessage().contains("Invalid input format"));
    }

    @Test
    void parseWithInvalidHeight() {
        Exception exception = assertThrows(InvalidNumberException.class, () ->
        {
            TextNumberParser parser = new TextNumberParser();
            List<TextDigit> textDigits = parser.parse(new String[]{
                    "    _  _     _  _  _  _  _ ",
                    "  | _| _||_||_ |_   ||_||_|",
                    "  ||_  _|  | _||_|  ||_| _|",
                    "  ||_  _|  | _||_|  ||_| _|"
            });
        });

        assertTrue(exception.getMessage().contains("Invalid input format"));
    }

    @Test
    void parseWithNullEntry() {
        Exception exception = assertThrows(InvalidNumberException.class, () ->
        {
            TextNumberParser parser = new TextNumberParser();
            List<TextDigit> textDigits = parser.parse(new String[]{
                    "    _  _     _  _  _  _  _ ",
                    null,
                    "  ||_  _|  | _||_|  ||_| _|"
            });
        });

        assertTrue(exception.getMessage().contains("Invalid input format"));
    }
}