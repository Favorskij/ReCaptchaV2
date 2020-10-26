package com.recaptcha;

import com.recaptcha.config.AppInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppInit.class)
public class AppStart {

    public static void main(String[] args) {

        SpringApplication.run(AppStart.class);

    }
}
