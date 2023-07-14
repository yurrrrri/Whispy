package com.syr.whispy.post.entity;

import com.syr.whispy.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Document(collection = "post")
public class Post extends BaseEntity {

    private String writer;

    private String content;

    private List<String> tags;

    private List<String> likeMembers;

    private List<String> comments;

}
