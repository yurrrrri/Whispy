package com.syr.whispy.post.code;

import com.syr.whispy.base.code.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LikeErrorCode implements Code {

    LIKE_NOT_EXISTS("F_L1", "존재하지 않는 좋아요 정보입니다.");

    private String code;
    private String msg;
}
