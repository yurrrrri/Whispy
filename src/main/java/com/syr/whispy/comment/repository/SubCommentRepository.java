package com.syr.whispy.comment.repository;

import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.comment.entity.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {

    List<SubComment> findByComment(Comment comment);
}
