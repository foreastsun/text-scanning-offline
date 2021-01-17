package org.example.textscanning.bean;

import org.example.textscanning.processor.ConsolePrintProcessor;
import org.example.textscanning.processor.NumberProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TextNumberTransformerTest {

    TextDigitMap map;
    TextNumberTransformer textNumberTransformer;
    ConsolePrintProcessor processor;

    @BeforeEach
    void setup() throws Exception {
        map = new TextDigitMap();
        Whitebox.setInternalState(map, "numberTemplatePath", "input\\number-template");
        Whitebox.setInternalState(map, "textNumberParser", new TextNumberParser());
        Whitebox.invokeMethod(map, "init");

        textNumberTransformer = new TextNumberTransformer();
        processor = mock(ConsolePrintProcessor.class);
        doNothing().when(processor).process(any());
        Whitebox.setInternalState(textNumberTransformer, "processor", processor);
        Whitebox.setInternalState(textNumberTransformer, "textNumberParser", new TextNumberParser());
        Whitebox.setInternalState(textNumberTransformer, "textDigitMap", map);

    }

    @Test
    void transform() throws InterruptedException {

        textNumberTransformer.transform(new String[] {
                "    _  _     _  _  _  _  _ ",
                "  | _| _||_||_ |_   ||_||_|",
                "  ||_  _|  | _||_|  ||_| _|"
        });
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(processor).process(argument.capture());
        assertEquals("123456789", argument.getValue());
    }

    @Test
    void transformWithException() throws InterruptedException {

        textNumberTransformer.transform(new String[] {
                "    _  _     _  _  _  _  _ ",
                "  | _| _||_||_ |_   ||_||_|  ",
                "  ||_  _|  | _||_|  ||_| _|"
        });

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(processor).process(argument.capture());
        assertEquals("ERROR", argument.getValue());    }
}