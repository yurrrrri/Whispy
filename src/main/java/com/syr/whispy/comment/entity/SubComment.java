package com.syr.whispy.comment.entity;

import com.syr.whispy.base.entity.BaseEntity;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.post.entity.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity(name = "sub_comment")
public class SubComment extends BaseEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @Max(150)
    private String content;
}
