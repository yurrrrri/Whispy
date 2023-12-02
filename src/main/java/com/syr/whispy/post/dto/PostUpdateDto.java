package com.syr.whispy.post.dto;

import com.syr.whispy.post.entity.Post;
import com.syr.whispy.post.entity.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostUpdateDto {

    private Post post;

    @NotBlank
    private String content;

    private List<Tag> tags;
}