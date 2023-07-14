package com.syr.whispy.member.service;

import com.syr.whispy.member.dto.DeletedMemberDto;
import com.syr.whispy.member.entity.DeletedMember;
import com.syr.whispy.member.repository.DeletedMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletedMemberService {

    private final DeletedMemberRepository deletedMemberRepository;

    public DeletedMember create(DeletedMemberDto dto) {
        return deletedMemberRepository.insert(
                DeletedMember.builder()
                        .id(UUID.randomUUID().toString())
                        .username(dto.getUsername())
                        .nickname(dto.getNickname())
                        .email(dto.getEmail())
                        .birthday(dto.getBirthday())
                        .createDate(LocalDateTime.now())
                        .build()
        );
    }

}
