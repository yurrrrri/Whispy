package com.syr.whispy.comment.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubCommentErrorCode implements Code {

    SUB_COMMENT_NOT_EXISTS("F_S1", "존재하지 않는 대댓글입니다.");

    private String code;
    private String msg;

}
