package com.syr.whispy.chat.entity;

import com.syr.whispy.base.entity.BaseEntity;
import com.syr.whispy.member.entity.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Message extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member sender;

    @Max(150)
    private String content;
}
