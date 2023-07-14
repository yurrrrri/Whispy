package com.syr.whispy.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SubCommentCreateDto {

    private String comment;

    private String writer;

    private String content;

}
