package com.syr.whispy.chat.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatErrorCode implements Code {

    CHAT_NOT_EXISTS("F_CH1", "존재하지 않는 채팅입니다.");

    private String code;
    private String msg;

}
