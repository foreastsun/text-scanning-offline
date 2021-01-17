package org.example.textscanning;

import org.example.textscanning.bean.TextNumberLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TextScanningMain implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java org.example.textscanning.TextScanningMain <path to input file>");
            System.exit(-1);
        }

        SpringApplication app = new SpringApplication(TextScanningMain.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        TextNumberLoader loader = applicationContext.getBean(TextNumberLoader.class);
        loader.loadAndTransform(args[0]);

    }
}