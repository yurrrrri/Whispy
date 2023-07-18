package com.syr.whispy.chat.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.chat.dto.MessageCreateDto;
import com.syr.whispy.chat.entity.Message;
import com.syr.whispy.chat.repository.MessageRepository;
import com.syr.whispy.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.chat.code.MessageErrorCode.MESSAGE_NOT_EXISTS;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final MemberService memberService;

    public Optional<Message> findById(String id) {
        return messageRepository.findById(id);
    }

    public Message findByIdAndGet(String id) {
        return findById(id)
                .orElseThrow(() -> new DataNotFoundException(MESSAGE_NOT_EXISTS));
    }

    public List<Message> findByChatId(String chatId) {
        return messageRepository.findByChat(chatId);
    }

    public Message create(MessageCreateDto dto) {
        memberService.verify(dto.getSender());
        chatService.verify(dto.getChat());

        return messageRepository.insert(Message.builder()
                .id(UUID.randomUUID().toString())
                .createDate(LocalDateTime.now())
                .chat(dto.getChat())
                .sender(dto.getSender())
                .content(dto.getContent())
                .build()
        );
    }

}
