package com.syr.whispy.member.service;

import com.syr.whispy.member.dto.DeletedMemberDto;
import com.syr.whispy.member.entity.DeletedMember;
import com.syr.whispy.member.repository.DeletedMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class DeletedMemberService {

    private final DeletedMemberRepository deletedMemberRepository;

    public DeletedMember create(DeletedMemberDto dto) {
        return deletedMemberRepository.save(
                DeletedMember.builder()
                        .username(dto.getUsername())
                        .nickname(dto.getNickname())
                        .email(dto.getEmail())
                        .birthday(dto.getBirthday())
                        .createdDate(LocalDateTime.now())
                        .build()
        );
    }
}
