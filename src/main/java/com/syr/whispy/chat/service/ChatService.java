package com.syr.whispy.chat.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.chat.entity.Chat;
import com.syr.whispy.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.syr.whispy.chat.code.ChatErrorCode.CHAT_NOT_EXISTS;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public Optional<Chat> findById(String id) {
        return chatRepository.findById(id);
    }

    public Chat findByIdAndGet(String id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException(CHAT_NOT_EXISTS));
    }
}
