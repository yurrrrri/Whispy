package com.syr.whispy.member.repository;

import com.syr.whispy.member.entity.Follow;
import com.syr.whispy.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, String> {

    Optional<Follow> findByMemberAndFollowedMember(Member fromMember, Member toMember);

    List<Follow> findByMember(Member member);
}
