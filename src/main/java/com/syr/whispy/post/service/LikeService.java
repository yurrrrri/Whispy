package com.syr.whispy.post.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.post.entity.Like;
import com.syr.whispy.post.entity.Post;
import com.syr.whispy.post.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.syr.whispy.post.code.LikeErrorCode.LIKE_NOT_EXISTS;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public Optional<Like> findById(Long id) {
        return likeRepository.findById(id);
    }

    public Like findByIdAndGet(Long id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException(LIKE_NOT_EXISTS));
    }

    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    public List<Like> findByPost(Post post) {
        return likeRepository.findByPost(post);
    }

    public List<Like> findByMember(Member member) {
        return likeRepository.findByMember(member);
    }

    @Transactional
    public Like create(Post post, Member member) {
        return Like.builder()
                .post(post)
                .member(member)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
