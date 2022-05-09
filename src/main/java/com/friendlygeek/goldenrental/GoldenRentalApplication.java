package com.friendlygeek.goldenrental;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
public class GoldenRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoldenRentalApplication.class, args);
    }

    @Bean
    public PromptProvider goldenRentalPromptProvider() {
        return () -> new AttributedString("GBS@CLI:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN));
    }
}
