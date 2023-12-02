package com.syr.whispy.member.repository;

import com.syr.whispy.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByUsername(String username);
}
