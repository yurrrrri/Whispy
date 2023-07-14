package com.syr.whispy.member.dto;

import com.syr.whispy.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class DeletedMemberDto {

    private String username;

    private String nickname;

    private String email;

    private LocalDateTime birthday;

    public DeletedMemberDto create(Member member) {
        return DeletedMemberDto.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .build();
    }

}
