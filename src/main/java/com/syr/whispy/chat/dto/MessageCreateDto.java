package com.syr.whispy.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MessageCreateDto {

    private String chat;

    private String sender;

    private String content;

}
