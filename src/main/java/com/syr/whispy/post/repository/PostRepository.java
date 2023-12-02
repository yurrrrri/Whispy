package com.syr.whispy.post.repository;

import com.syr.whispy.member.entity.Member;
import com.syr.whispy.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findByWriter(Member writer);
}
