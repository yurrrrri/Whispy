package com.syr.whispy.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentCreateDto {

    private String writer;

    private String post;

    private String content;

}
