package ru.kpfu.sweetlife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
public class SweetLifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweetLifeApplication.class, args);
    }

}
