package com.syr.whispy.post.entity;

import com.syr.whispy.base.entity.BaseEntity;
import com.syr.whispy.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity(name = "post")
public class Post extends BaseEntity implements Comparable<Post> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @Min(1)
    private String content;

    @Builder.Default
    @OneToMany
    private List<Tag> tags = new ArrayList<>();

    @Override
    public int compareTo(Post other) {
        return other.getCreatedDate().compareTo(this.getCreatedDate());
    }
}
