package com.syr.whispy.post.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.post.dto.PostCreateDto;
import com.syr.whispy.post.dto.PostUpdateDto;
import com.syr.whispy.post.entity.Post;
import com.syr.whispy.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.syr.whispy.post.code.PostErrorCode.POST_NOT_EXISTS;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Post findByIdAndGet(Long id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException(POST_NOT_EXISTS));
    }

    @Transactional(readOnly = true)
    public List<Post> findByWriter(Member writer) {
        return postRepository.findByWriter(writer);
    }

    public Post create(PostCreateDto dto) {
        return postRepository.save(Post.builder()
                .writer(dto.getWriter())
                .content(dto.getContent())
                .tags(dto.getTags())
                .createdDate(LocalDateTime.now())
                .build()
        );
    }

    public Post update(PostUpdateDto dto) {
        Post post = findByIdAndGet(dto.getPostId());

        return postRepository.save(post.toBuilder()
                .content(dto.getContent())
                .tags(dto.getTags())
                .modifiedDate(LocalDateTime.now())
                .build()
        );
    }

    public void softDelete(Long id) {
        Post post = findByIdAndGet(id);

        postRepository.save(post.toBuilder()
                .deletedDate(LocalDateTime.now())
                .build()
        );
    }

    public void hardDelete(Long id) {
        if (!postRepository.existsById(id)) {
            throw new DataNotFoundException(POST_NOT_EXISTS);
        }

        postRepository.deleteById(id);
    }
}
