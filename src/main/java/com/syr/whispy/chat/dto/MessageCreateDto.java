package com.syr.whispy.chat.dto;

import com.syr.whispy.chat.entity.Chat;
import com.syr.whispy.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MessageCreateDto {

    private Chat chat;
    private Member sender;
    private String content;
}
