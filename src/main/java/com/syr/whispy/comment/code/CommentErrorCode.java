package com.syr.whispy.comment.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements Code {

    COMMENT_NOT_EXISTS("F_C1", "존재하지 않는 댓글입니다.");

    private String code;
    private String msg;

}
