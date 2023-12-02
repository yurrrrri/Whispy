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
import java.util.UUID;

import static com.syr.whispy.post.code.PostErrorCode.POST_NOT_EXISTS;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Post findByIdAndGet(String id) {
        Optional<Post> opPost = findById(id);

        if (opPost.isEmpty()) {
            throw new DataNotFoundException(POST_NOT_EXISTS);
        }

        return opPost.get();
    }

    @Transactional(readOnly = true)
    public List<Post> findByWriter(Member writer) {
        return postRepository.findByWriter(writer);
    }

    public Post create(PostCreateDto dto) {
        return postRepository.save(Post.builder()
                .id(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .writer(dto.getWriter())
                .content(dto.getContent())
                .tags(dto.getTags())
                .build()
        );
    }

    public Post update(PostUpdateDto dto) {
        Post post = findByIdAndGet(dto.getPost().getId());

        return postRepository.save(post.toBuilder()
                .content(dto.getContent())
                .tags(dto.getTags())
                .modifiedDate(LocalDateTime.now())
                .build()
        );
    }

    public void softDelete(String id) {
        Post post = findByIdAndGet(id);

        postRepository.save(post.toBuilder()
                .deletedDate(LocalDateTime.now())
                .build()
        );
    }

    public void hardDelete(String id) {
        if (!postRepository.existsById(id)) {
            throw new DataNotFoundException(POST_NOT_EXISTS);
        }

        postRepository.deleteById(id);
    }
}
