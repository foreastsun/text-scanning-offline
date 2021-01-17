package org.example.textscanning.bean;

import org.example.textscanning.constant.Constant;
import org.example.textscanning.exception.InvalidNumberException;
import org.example.textscanning.model.TextDigit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TextNumberParser {

    public List<TextDigit> parse(String[] lines) throws InvalidNumberException {
        return parse(lines, Constant.LINE_LENGTH);
    }

    public List<TextDigit> parse(String[] lines, int validLength) throws InvalidNumberException {
        validate(lines, validLength);

        List<TextDigit> result = new ArrayList<>();
        int len = lines[0].length();
        for (int i = 0; i < len; i += Constant.NUMBER_WIDTH) {

            String[] number = new String[Constant.NUMBER_HEIGHT];
            for (int j = 0; j < Constant.NUMBER_HEIGHT; j++) {
                number[j] = lines[j].substring(i, i + Constant.NUMBER_WIDTH);
            }

            TextDigit tn = new TextDigit(number);
            result.add(tn);

        }
        return result;
    }

    private void validate(String[] lines, int validLength) throws InvalidNumberException {
        if (lines.length != Constant.NUMBER_HEIGHT
                || lines[0] == null
                || lines[1] == null
                || lines[2] == null
                || lines[0].length() != validLength
                || lines[1].length() != validLength
                || lines[2].length() != validLength)

            throw new InvalidNumberException("Invalid input format.");
    }
}
