package com.syr.whispy.chat.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageErrorCode implements Code {

    MESSAGE_NOT_EXISTS("F_MS1", "존재하지 않는 메시지입니다.");

    private String code;
    private String msg;

}
