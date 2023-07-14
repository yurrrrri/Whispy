package com.syr.whispy.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SubCommentUpdateDto {

    private String subComment;

    private String content;

}
