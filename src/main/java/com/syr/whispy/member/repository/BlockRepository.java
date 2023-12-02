package com.syr.whispy.member.repository;

import com.syr.whispy.member.entity.Block;
import com.syr.whispy.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlockRepository extends JpaRepository<Block, Long> {

    Optional<Block> findByMemberAndBlockedMember(Member fromMember, Member toMember);
}
