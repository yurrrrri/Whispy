package com.syr.whispy.post.service;

import com.syr.whispy.post.entity.Tag;
import com.syr.whispy.post.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.post.code.TagErrorCode.TAG_ALREADY_EXISTS;
import static com.syr.whispy.post.code.TagErrorCode.TAG_NOT_EXISTS;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Optional<Tag> findById(String id) {
        return tagRepository.findById(id);
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag create(String name) {
        verifyExistsTagByName(name);

        return tagRepository.insert(Tag.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .build()
        );
    }

    public void delete(String tagId) {
        verifyNotExistsTagById(tagId);

        tagRepository.deleteById(tagId);
    }

    private void verifyExistsTagByName(String name) {
        if (tagRepository.findByName(name).isPresent()) {
            throw new DuplicateKeyException(TAG_ALREADY_EXISTS.getMsg());
        }
    }

    private void verifyNotExistsTagById(String id) {
        if (tagRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, TAG_NOT_EXISTS.getMsg()
            );
        }
    }

}
