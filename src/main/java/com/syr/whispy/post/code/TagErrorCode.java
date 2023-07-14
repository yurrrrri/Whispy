package com.syr.whispy.post.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TagErrorCode implements Code {

    TAG_ALREADY_EXISTS("F_T1", "이미 존재하는 태그입니다."),
    TAG_NOT_EXISTS("F_T2", "존재하지 않는 태그입니다.");

    private String code;
    private String msg;

}
