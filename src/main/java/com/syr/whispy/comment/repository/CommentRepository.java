package com.syr.whispy.comment.repository;

import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
}
