package com.syr.whispy.post.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.post.dto.PostCreateDto;
import com.syr.whispy.post.dto.PostUpdateDto;
import com.syr.whispy.post.entity.Post;
import com.syr.whispy.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.post.code.PostErrorCode.POST_NOT_EXISTS;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }

    public Post findByIdAndGet(String id) {
        Optional<Post> opPost = findById(id);

        if (opPost.isEmpty()) {
            throw new DataNotFoundException(POST_NOT_EXISTS);
        }

        return opPost.get();
    }

    public Post create(PostCreateDto dto) {
        return postRepository.insert(Post.builder()
                .id(UUID.randomUUID().toString())
                .createDate(LocalDateTime.now())
                .writer(dto.getWriter())
                .content(dto.getContent())
                .tags(dto.getTags())
                .build()
        );
    }

    public Post update(PostUpdateDto dto) {
        Post post = findByIdAndGet(dto.getPost());

        return postRepository.save(post.toBuilder()
                .content(dto.getContent())
                .tags(dto.getTags())
                .modifyDate(LocalDateTime.now())
                .build()
        );
    }

    public void softDelete(String postId) {
        Post post = findByIdAndGet(postId);

        postRepository.save(post.toBuilder()
                .deleteDate(LocalDateTime.now())
                .build()
        );
    }

    public void hardDelete(String postId) {
        Post post = findByIdAndGet(postId);

        postRepository.delete(post);
    }

}
