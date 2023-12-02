package com.syr.whispy.post.repository;

import com.syr.whispy.post.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, String> {

    Optional<Tag> findByName(String name);
}
