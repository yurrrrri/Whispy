package com.syr.whispy.post.repository;

import com.syr.whispy.member.entity.Member;
import com.syr.whispy.post.entity.Like;
import com.syr.whispy.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByPost(Post post);

    List<Like> findByMember(Member member);
}
