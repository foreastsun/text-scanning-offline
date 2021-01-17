package org.example.textscanning.bean;

import org.example.textscanning.constant.Constant;
import org.example.textscanning.exception.InvalidNumberException;
import org.example.textscanning.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class TextNumberLoader {

    Logger logger = LoggerFactory.getLogger(TextNumberLoader.class);


    @Autowired
    private TextNumberTransformer textNumberTransformer;

    public void loadAndTransform(String filePath) throws IOException, InvalidNumberException {

        Path path = Paths.get(filePath);
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            int count = 0;
            String[] lines = new String[Constant.NUMBER_HEIGHT];
            while ((line = bufferedReader.readLine()) != null) {
                if (count == Constant.NUMBER_HEIGHT) {
                    if (line.trim().length() > 0) throw new InvalidNumberException("Invalid number format:\n" + Utils.toString(lines));

                    textNumberTransformer.transform(lines);
                    lines = new String[Constant.NUMBER_HEIGHT];
                    count = 0;
                    continue;
                }
                lines[count++] = line;
            }

            if (count == Constant.NUMBER_HEIGHT) {
                textNumberTransformer.transform(lines);
            } else if (count > 0) {
                logger.warn("Invalid number format \n{}", Utils.toString(lines));
            }
        }
    }

}
