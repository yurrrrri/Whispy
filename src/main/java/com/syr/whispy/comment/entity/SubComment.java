package com.syr.whispy.comment.entity;

import com.syr.whispy.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class SubComment extends BaseEntity {

    private String writer;
    private String comment;
    private String content;
}
