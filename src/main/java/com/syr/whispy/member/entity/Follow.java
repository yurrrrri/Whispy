package com.syr.whispy.member.entity;

import com.syr.whispy.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Follow extends BaseEntity {

    private String member;
    private String followedMember;
}
