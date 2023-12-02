package com.syr.whispy.comment.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.comment.dto.SubCommentCreateDto;
import com.syr.whispy.comment.dto.SubCommentUpdateDto;
import com.syr.whispy.comment.entity.Comment;
import com.syr.whispy.comment.entity.SubComment;
import com.syr.whispy.comment.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.comment.code.SubCommentErrorCode.SUB_COMMENT_NOT_EXISTS;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SubCommentService {

    private final SubCommentRepository subCommentRepository;

    public Optional<SubComment> findById(String id) {
        return subCommentRepository.findById(id);
    }

    public SubComment findByIdAndGet(String id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException(SUB_COMMENT_NOT_EXISTS));
    }

    public List<SubComment> findByComment(Comment comment) {
        return subCommentRepository.findByComment(comment);
    }

    @Transactional
    public SubComment create(SubCommentCreateDto dto) {
        return subCommentRepository.save(SubComment.builder()
                .id(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .writer(dto.getWriter())
                .post(dto.getPost())
                .comment(dto.getComment())
                .content(dto.getContent())
                .build()
        );
    }

    @Transactional
    public SubComment update(String id, SubCommentUpdateDto dto) {
        SubComment subComment = findByIdAndGet(id);

        return subCommentRepository.save(subComment.toBuilder()
                .modifiedDate(LocalDateTime.now())
                .content(dto.getContent())
                .build()
        );
    }

    @Transactional
    public String softDelete(String id) {
        SubComment subComment = findByIdAndGet(id);
        String postId = subComment.getPost().getId();

        subCommentRepository.save(subComment.toBuilder()
                .deletedDate(LocalDateTime.now())
                .build());

        return postId;
    }

    @Transactional
    public String hardDelete(String id) {
        if (!subCommentRepository.existsById(id)) {
            throw new DataNotFoundException(SUB_COMMENT_NOT_EXISTS);
        }

        String postId = findByIdAndGet(id).getPost().getId();
        subCommentRepository.deleteById(id);

        return postId;
    }
}
