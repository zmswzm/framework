package com.neal.nealcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })
public class NealCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(NealCoreApplication.class, args);
    }
}
