package org.example.textscanning.bean;

import org.example.textscanning.constant.Constant;
import org.example.textscanning.exception.InvalidNumberException;
import org.example.textscanning.model.TextDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TextDigitMap {

    @Value("${number.template.path}")
    private String numberTemplatePath;

    @Autowired
    private TextNumberParser textNumberParser;

    private Map<TextDigit, Character> textDigitMap;


    @PostConstruct
    private void init() throws IOException, InvalidNumberException {

        String[] lines = Files.readAllLines(Path.of(numberTemplatePath))
                .stream().map(l -> l.replaceAll("\\.", " "))
                .toArray(String[]::new);

        List<TextDigit> numbers = textNumberParser.parse(lines, Constant.TEMPLATE_LINE_LENGTH);
        textDigitMap = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            textDigitMap.put(numbers.get(i), Character.forDigit(i, 10));
        }
    }

    public Character getDigit(TextDigit textNumber) {
        return textDigitMap.get(textNumber);
    }

    public String getNumberString(List<TextDigit> digits) {
        StringBuilder sb = new StringBuilder();
        boolean ill = false;
        for (TextDigit td : digits) {
            Character c = getDigit(td);
            ill = ill || (c == null);
            sb.append(c == null ? '?' : c);
        }
        if (ill) sb.append("ILL");
        return sb.toString();
    }

}
