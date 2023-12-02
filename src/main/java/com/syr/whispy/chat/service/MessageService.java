package com.syr.whispy.chat.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.chat.dto.MessageCreateDto;
import com.syr.whispy.chat.entity.Chat;
import com.syr.whispy.chat.entity.Message;
import com.syr.whispy.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.syr.whispy.chat.code.MessageErrorCode.MESSAGE_NOT_EXISTS;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    public Message findByIdAndGet(Long id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException(MESSAGE_NOT_EXISTS));
    }

    public List<Message> findByChat(Chat chat) {
        return messageRepository.findByChat(chat);
    }

    @Transactional
    public Message create(MessageCreateDto dto) {
        return messageRepository.save(Message.builder()
                .chat(dto.getChat())
                .sender(dto.getSender())
                .content(dto.getContent())
                .createdDate(LocalDateTime.now())
                .build()
        );
    }
}
