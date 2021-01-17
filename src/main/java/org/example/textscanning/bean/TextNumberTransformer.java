package org.example.textscanning.bean;

import org.example.textscanning.exception.InvalidNumberException;
import org.example.textscanning.model.TextDigit;
import org.example.textscanning.processor.NumberProcessor;
import org.example.textscanning.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TextNumberTransformer {

    Logger logger = LoggerFactory.getLogger(TextNumberTransformer.class);

    @Autowired
    private NumberProcessor processor;

    @Autowired
    private TextNumberParser textNumberParser;

    @Autowired
    private TextDigitMap textDigitMap;

    public void transform(String[] lines)  {

        try {
            List<TextDigit> digits = textNumberParser.parse(lines);
            String number = textDigitMap.getNumberString(digits);
            processor.process(number);

        } catch (InvalidNumberException e) {
            processor.process("ERROR");
            logger.error("Exception when processing: \n{}\n", Utils.toString(lines), e);
        }

    }

}
