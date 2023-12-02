package com.syr.whispy.post.entity;

import com.syr.whispy.base.entity.BaseEntity;
import com.syr.whispy.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity(name = "likes")
public class Like extends BaseEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
