package com.syr.whispy.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentCreateDto {

    @NotBlank
    private String writer;

    private String post;

    @NotBlank
    private String content;

}
