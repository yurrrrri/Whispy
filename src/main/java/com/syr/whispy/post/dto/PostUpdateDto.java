package com.syr.whispy.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostUpdateDto {

    @NotBlank
    private String post;

    @NotBlank
    private String content;

    private List<String> tags;

}