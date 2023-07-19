package com.syr.whispy.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MemberUpdateDto {

    private String memberId;

    private String nickname;

    private String email;

    private LocalDateTime birthday;

    private String image;

}
