package com.syr.whispy.post.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.base.exception.DuplicateFieldException;
import com.syr.whispy.post.entity.Tag;
import com.syr.whispy.post.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.post.code.TagErrorCode.TAG_ALREADY_EXISTS;
import static com.syr.whispy.post.code.TagErrorCode.TAG_NOT_EXISTS;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    public Optional<Tag> findById(String id) {
        return tagRepository.findById(id);
    }

    public Tag findByIdAndGet(String id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(TAG_NOT_EXISTS));
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    public Tag create(String name) {
        verifyNotExistsTagByName(name);

        return tagRepository.insert(Tag.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .build()
        );
    }

    public void delete(String id) {
        verifyExistsTagById(id);

        tagRepository.deleteById(id);
    }

    private void verifyNotExistsTagByName(String name) {
        if (tagRepository.findByName(name).isPresent()) {
            throw new DuplicateFieldException(TAG_ALREADY_EXISTS);
        }
    }

    private void verifyExistsTagById(String id) {
        if (tagRepository.findById(id).isEmpty()) {
            throw new DataNotFoundException(TAG_NOT_EXISTS);
        }
    }

}
