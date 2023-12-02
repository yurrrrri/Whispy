package com.syr.whispy.comment.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.comment.dto.CommentCreateDto;
import com.syr.whispy.comment.dto.CommentUpdateDto;
import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.comment.repository.CommentRepository;
import com.syr.whispy.member.service.MemberService;
import com.syr.whispy.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.comment.code.CommentErrorCode.COMMENT_NOT_EXISTS;
import static com.syr.whispy.member.code.MemberErrorCode.MEMBER_NOT_EXISTS;
import static com.syr.whispy.post.code.PostErrorCode.POST_NOT_EXISTS;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final PostService postService;

    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    public Comment findByIdAndGet(String id) {
        Optional<Comment> opComment = findById(id);

        if (opComment.isEmpty()) {
            throw new DataNotFoundException(COMMENT_NOT_EXISTS);
        }

        return opComment.get();
    }

    public List<Comment> findByPostId(String postId) {
        return commentRepository.findByPost(postId);
    }

    public Comment create(CommentCreateDto dto) {
        if (memberService.findById(dto.getWriter()).isEmpty()) {
            throw new DataNotFoundException(MEMBER_NOT_EXISTS);
        }

        if (postService.findById(dto.getPost()).isEmpty()) {
            throw new DataNotFoundException(POST_NOT_EXISTS);
        }

        return commentRepository.insert(Comment.builder()
                .id(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .writer(dto.getWriter())
                .post(dto.getPost())
                .content(dto.getContent())
                .build()
        );
    }

    public Comment update(CommentUpdateDto dto) {
        Comment comment = findByIdAndGet(dto.getComment());

        return commentRepository.save(comment.toBuilder()
                .modifiedDate(LocalDateTime.now())
                .content(dto.getContent())
                .build()
        );
    }

    public void softDelete(String id) {
        Comment comment = findByIdAndGet(id);

        commentRepository.save(comment.toBuilder()
                .deletedDate(LocalDateTime.now())
                .build());
    }

    public void hardDelete(String id) {
        if (!commentRepository.existsById(id)) {
            throw new DataNotFoundException(COMMENT_NOT_EXISTS);
        }

        commentRepository.deleteById(id);
    }

}
