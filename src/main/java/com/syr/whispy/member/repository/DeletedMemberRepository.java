package com.syr.whispy.member.repository;

import com.syr.whispy.member.entity.DeletedMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedMemberRepository extends JpaRepository<DeletedMember, Long> {
}
