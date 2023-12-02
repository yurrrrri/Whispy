package com.syr.whispy.member.repository;

import com.syr.whispy.member.entity.DeletedMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedMemberRepository extends JpaRepository<DeletedMember, String> {
}
