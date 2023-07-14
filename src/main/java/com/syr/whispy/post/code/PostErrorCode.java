package com.syr.whispy.post.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements Code {

    POST_NOT_EXISTS("F_P1", "존재하지 않는 글입니다.");

    private String code;
    private String msg;

}
