package com.syr.whispy.spamWord.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpamWordErrorCode implements Code {

    SPAM_WORD_ALREADY_EXISTS("F_SW1", "이미 존재하는 금지어입니다.");

    private String code;
    private String msg;

}
