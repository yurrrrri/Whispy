package com.syr.whispy.member.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements Code {

    MEMBER_ALREADY_EXISTS("F_M_1", "이미 존재하는 회원입니다.");

    private String code;
    private String msg;

}