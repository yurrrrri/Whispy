package com.syr.whispy.comment.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.comment.dto.CommentCreateDto;
import com.syr.whispy.comment.dto.CommentUpdateDto;
import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.comment.repository.CommentRepository;
import com.syr.whispy.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.comment.code.CommentErrorCode.COMMENT_NOT_EXISTS;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Comment findByIdAndGet(String id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException(COMMENT_NOT_EXISTS));
    }

    @Transactional(readOnly = true)
    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public Comment create(CommentCreateDto dto) {
        return commentRepository.save(Comment.builder()
                .id(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .writer(dto.getWriter())
                .post(dto.getPost())
                .content(dto.getContent())
                .build()
        );
    }

    public Comment update(String commentId, CommentUpdateDto dto) {
        Comment comment = findByIdAndGet(commentId);

        return commentRepository.save(comment.toBuilder()
                .modifiedDate(LocalDateTime.now())
                .content(dto.getContent())
                .build()
        );
    }

    public String softDelete(String id) {
        Comment comment = findByIdAndGet(id);
        String postId = comment.getPost().getId();

        commentRepository.save(comment.toBuilder()
                .deletedDate(LocalDateTime.now())
                .build());

        return postId;
    }

    public String hardDelete(String id) {
        if (!commentRepository.existsById(id)) {
            throw new DataNotFoundException(COMMENT_NOT_EXISTS);
        }

        Comment comment = findByIdAndGet(id);
        String postId = comment.getPost().getId();
        commentRepository.delete(comment);

        return postId;
    }
}
