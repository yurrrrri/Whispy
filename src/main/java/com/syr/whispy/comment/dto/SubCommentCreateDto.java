package com.syr.whispy.comment.dto;

import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SubCommentCreateDto {

    private Member writer;
    private Post post;
    private Comment comment;

    @NotBlank
    private String content;
}
