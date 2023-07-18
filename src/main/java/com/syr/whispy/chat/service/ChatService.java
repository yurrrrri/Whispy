package com.syr.whispy.chat.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.chat.entity.Chat;
import com.syr.whispy.chat.repository.ChatRepository;
import com.syr.whispy.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.syr.whispy.chat.code.ChatErrorCode.CHAT_NOT_EXISTS;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final MemberService memberService;

    public Optional<Chat> findById(String id) {
        return chatRepository.findById(id);
    }

    public Chat findByIdAndGet(String id) {
        verify(id);

        return chatRepository.findById(id).get();
    }

    public void verify(String chatId) {
        Chat chat = findById(chatId)
                .orElseThrow(() -> new DataNotFoundException(CHAT_NOT_EXISTS));

        List<String> members = chat.getMembers();
        memberService.verify(members.get(0), members.get(1));
    }

}
