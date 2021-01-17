package org.example.textscanning.processor;

import org.junit.jupiter.api.Test;


class ConsolePrintProcessorTest {

    @Test
    void process() {
        ConsolePrintProcessor processor = new ConsolePrintProcessor();
        processor.process("12343");
    }

}