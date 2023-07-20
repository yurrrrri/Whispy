package com.syr.whispy.member.repository;

import com.syr.whispy.member.entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends MongoRepository<Follow, String> {

    Optional<Follow> findByMemberAndFollowedMember(String fromMemberId, String toMemberId);

    List<Follow> findByMember(String memberId);

}
