package com.syr.whispy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class WhispyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhispyApplication.class, args);
    }

}
