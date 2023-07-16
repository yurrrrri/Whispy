package com.syr.whispy.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostCreateDto {

    @NotBlank
    private String writer;

    @NotBlank
    private String content;

    private List<String> tags;

}