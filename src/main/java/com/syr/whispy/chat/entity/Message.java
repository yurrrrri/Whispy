package com.syr.whispy.chat.entity;

import com.syr.whispy.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Message extends BaseEntity {

    private String chat;
    private String sender;
    private String content;
}
