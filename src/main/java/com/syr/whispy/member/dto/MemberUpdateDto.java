package com.syr.whispy.member.dto;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
    @Max(50)
    private String description;
}
