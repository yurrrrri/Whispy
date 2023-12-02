package com.syr.whispy.member.repository;

import com.syr.whispy.member.entity.Block;
import com.syr.whispy.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockRepository extends JpaRepository<Block, String> {

    Optional<Block> findByMemberAndBlockedMember(Member fromMember, Member toMember);
}
