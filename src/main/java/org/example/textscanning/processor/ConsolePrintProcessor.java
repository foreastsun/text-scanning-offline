package org.example.textscanning.processor;

import org.springframework.stereotype.Component;

@Component
public class ConsolePrintProcessor implements NumberProcessor {

    @Override
    public void process(String number) {
        System.out.println(number);
    }

}
