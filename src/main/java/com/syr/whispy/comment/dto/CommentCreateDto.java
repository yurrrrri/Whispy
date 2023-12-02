package com.syr.whispy.comment.dto;

import com.syr.whispy.member.entity.Member;
import com.syr.whispy.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentCreateDto {

    private Member writer;
    private Post post;

    @NotBlank
    private String content;
}
