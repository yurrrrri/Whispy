package com.syr.whispy.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostUpdateDto {

    private String content;

    private List<String> tags;

}