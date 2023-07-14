package com.syr.whispy.member.repository;

import com.syr.whispy.member.entity.DeletedMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedMemberRepository extends MongoRepository<DeletedMember, String> {
}
