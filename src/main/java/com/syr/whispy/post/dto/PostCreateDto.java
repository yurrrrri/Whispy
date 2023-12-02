package com.syr.whispy.post.dto;

import com.syr.whispy.member.entity.Member;
import com.syr.whispy.post.entity.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostCreateDto {

    private Member writer;

    @NotBlank
    private String content;

    private List<Tag> tags;
}