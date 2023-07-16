package com.syr.whispy.spamWord.repository;

import com.syr.whispy.spamWord.entity.SpamWord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpamWordRepository extends MongoRepository<SpamWord, String> {

    Optional<SpamWord> findByWord(String word);
}
