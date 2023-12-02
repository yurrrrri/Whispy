package com.syr.whispy.comment.repository;

import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.comment.entity.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCommentRepository extends JpaRepository<SubComment, String> {

    List<SubComment> findByComment(Comment comment);
}
