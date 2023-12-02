package com.syr.whispy.spamWord.repository;

import com.syr.whispy.spamWord.entity.SpamWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpamWordRepository extends JpaRepository<SpamWord, Long> {

    Optional<SpamWord> findByWord(String word);
}
