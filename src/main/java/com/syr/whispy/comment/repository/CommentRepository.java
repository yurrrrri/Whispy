package com.syr.whispy.comment.repository;

import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findByPost(Post post);
}
