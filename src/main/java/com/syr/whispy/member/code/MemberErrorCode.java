package com.syr.whispy.member.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements Code {

    USERNAME_ALREADY_EXISTS("F_M1", "이미 존재하는 회원 아이디입니다."),
    MEMBER_NOT_EXISTS("F_M1", "존재하지 않는 회원입니다."),
    ALREADY_BLOCKED("F_M3", "이미 차단한 회원입니다."),
    ALREADY_FOLLOWED("F_M3", "이미 팔로우한 회원입니다.");

    private String code;
    private String msg;

}