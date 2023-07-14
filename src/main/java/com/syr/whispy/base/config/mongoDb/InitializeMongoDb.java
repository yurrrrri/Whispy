package com.syr.whispy.base.config.mongoDb;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitializeMongoDb implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) {
        List<String> collections = List.of(
                "chat", "message", "comment", "sub_comment", "block", "deleted_member",
                "follow", "member", "post", "tag", "spam_word"
        );

        for(String collection : collections) {
            if(mongoTemplate.collectionExists(collection)) {
                mongoTemplate.dropCollection(collection);
            }
            mongoTemplate.createCollection(collection);
        }
    }

}
