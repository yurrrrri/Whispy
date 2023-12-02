package com.syr.whispy.spamWord.service;

import com.syr.whispy.base.exception.DuplicateFieldException;
import com.syr.whispy.spamWord.dto.SpamWordCreateDto;
import com.syr.whispy.spamWord.entity.SpamWord;
import com.syr.whispy.spamWord.repository.SpamWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.spamWord.code.SpamWordErrorCode.SPAM_WORD_ALREADY_EXISTS;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SpamWordService {

    private final SpamWordRepository spamWordRepository;

    public Optional<SpamWord> findById(String id) {
        return spamWordRepository.findById(id);
    }

    public Optional<SpamWord> findByWord(String word) {
        return spamWordRepository.findByWord(word);
    }

    public List<SpamWord> findAll() {
        return spamWordRepository.findAll();
    }

    @Transactional
    public SpamWord create(SpamWordCreateDto dto) {
        if (spamWordRepository.findByWord(dto.getWord()).isPresent()) {
            throw new DuplicateFieldException(SPAM_WORD_ALREADY_EXISTS);
        }

        return spamWordRepository.save(SpamWord.builder()
                .id(UUID.randomUUID().toString())
                .word(dto.getWord())
                .build()
        );
    }
}
