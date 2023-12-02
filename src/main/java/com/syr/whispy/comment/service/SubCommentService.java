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

import static com.syr.whispy.comment.code.SubCommentErrorCode.SUB_COMMENT_NOT_EXISTS;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SubCommentService {

    private final SubCommentRepository subCommentRepository;

    public Optional<SubComment> findById(Long id) {
        return subCommentRepository.findById(id);
    }

    public SubComment findByIdAndGet(Long id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException(SUB_COMMENT_NOT_EXISTS));
    }

    public List<SubComment> findByComment(Comment comment) {
        return subCommentRepository.findByComment(comment);
    }

    @Transactional
    public SubComment create(SubCommentCreateDto dto) {
        return subCommentRepository.save(SubComment.builder()
                .writer(dto.getWriter())
                .post(dto.getPost())
                .comment(dto.getComment())
                .content(dto.getContent())
                .createdDate(LocalDateTime.now())
                .build()
        );
    }

    @Transactional
    public SubComment update(Long id, SubCommentUpdateDto dto) {
        SubComment subComment = findByIdAndGet(id);

        return subCommentRepository.save(subComment.toBuilder()
                .content(dto.getContent())
                .modifiedDate(LocalDateTime.now())
                .build()
        );
    }

    @Transactional
    public Long softDelete(Long id) {
        SubComment subComment = findByIdAndGet(id);
        Long postId = subComment.getPost().getId();

        subCommentRepository.save(subComment.toBuilder()
                .deletedDate(LocalDateTime.now())
                .build());

        return postId;
    }

    @Transactional
    public Long hardDelete(Long id) {
        if (!subCommentRepository.existsById(id)) {
            throw new DataNotFoundException(SUB_COMMENT_NOT_EXISTS);
        }

        Long postId = findByIdAndGet(id).getPost().getId();
        subCommentRepository.deleteById(id);

        return postId;
    }
}
