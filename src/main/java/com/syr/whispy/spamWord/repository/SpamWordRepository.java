package com.syr.whispy.spamWord.repository;

import com.syr.whispy.spamWord.entity.SpamWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpamWordRepository extends JpaRepository<SpamWord, String> {

    Optional<SpamWord> findByWord(String word);
}
