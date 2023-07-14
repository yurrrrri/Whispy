package com.syr.whispy.comment.repository;

import com.syr.whispy.comment.entity.SubComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCommentRepository extends MongoRepository<SubComment, String> {

    List<SubComment> findByComment(String id);

}
