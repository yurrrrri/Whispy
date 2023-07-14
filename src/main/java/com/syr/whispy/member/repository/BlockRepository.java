package com.syr.whispy.member.repository;

import com.syr.whispy.member.entity.Block;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends MongoRepository<Block, String> {
}
