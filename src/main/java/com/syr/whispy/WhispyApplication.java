package com.syr.whispy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WhispyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhispyApplication.class, args);
    }

}
