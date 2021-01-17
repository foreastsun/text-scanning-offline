package org.example.textscanning.bean;

import org.example.textscanning.exception.InvalidNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.powermock.reflect.Whitebox;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextNumberLoaderTest {

    TextNumberTransformer textNumberTransformer;
    TextNumberLoader textNumberLoader;

    @BeforeEach
    void setup() {
        textNumberTransformer = mock(TextNumberTransformer.class);
        textNumberLoader = new TextNumberLoader();
        Whitebox.setInternalState(textNumberLoader, "textNumberTransformer", textNumberTransformer);
    }

    @Test
    void loadSingeChunk() throws IOException, InvalidNumberException {
        textNumberLoader.loadAndTransform("input\\singleChunk");

        ArgumentCaptor<String[]> argument = ArgumentCaptor.forClass(String[].class);
        verify(textNumberTransformer).transform(argument.capture());

        String[] expected = new String[]{
                " _  _  _  _  _  _  _  _  _ ",
                "| || || || || || || || || |",
                "|_||_||_||_||_||_||_||_||_|"
        };
        String[] actual = argument.getValue();
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
    }

    @Test
    void loadMultipleChunks() throws IOException, InvalidNumberException {
        textNumberLoader.loadAndTransform("input\\multipleChunks");

        ArgumentCaptor<String[]> argument = ArgumentCaptor.forClass(String[].class);
        verify(textNumberTransformer, times(3)).transform(argument.capture());

        String[] expected = new String[]{
                "    _  _     _  _  _  _  _ ",
                "  | _| _||_||_ |_   ||_||_|",
                "  ||_  _|  | _||_|  ||_| _|"
        };

        String[] actual = argument.getValue();
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
    }

    @Test
    void loadWithInvalidHeightWithFourLines() {
        Exception exception = assertThrows(InvalidNumberException.class, () -> {
            textNumberLoader.loadAndTransform("src\\test\\input\\invalidHeightWithFourLines");
        });

        assertTrue(exception.getMessage().contains("Invalid number format"));
    }

    @Test
    void loadWithInvalidHeightWithTwoLines() throws IOException, InvalidNumberException {
        textNumberLoader.loadAndTransform("src\\test\\input\\invalidHeightWithTwoLines");
        verify(textNumberTransformer, times(0)).transform(any());
    }
}