package com.syr.whispy.member.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements Code {

    MEMBER_CREATED("S_M1", "회원 가입이 완료되었습니다.");

    private String code;
    private String msg;

}