package com.syr.whispy.member.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.base.exception.DuplicateFieldException;
import com.syr.whispy.member.dto.MemberUpdateDto;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.member.code.MemberErrorCode.MEMBER_NOT_EXISTS;
import static com.syr.whispy.member.code.MemberErrorCode.USERNAME_ALREADY_EXISTS;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    public Member findByIdAndGet(String id) {
        return findById(id)
                .orElseThrow(() -> new DataNotFoundException(MEMBER_NOT_EXISTS));
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findByUsernameAndGet(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(MEMBER_NOT_EXISTS));
    }

    public void verify(String memberId) {
        if (findById(memberId).isEmpty()) {
            throw new DataNotFoundException(MEMBER_NOT_EXISTS);
        }
    }

    public void verify(String member1Id, String member2Id) {
        if (findById(member1Id).isEmpty() || findById(member2Id).isEmpty()) {
            throw new DataNotFoundException(MEMBER_NOT_EXISTS);
        }
    }

    public Member join(String username) {
        if (findByUsername(username).isPresent()) {
            throw new DuplicateFieldException(USERNAME_ALREADY_EXISTS);
        }

        return memberRepository.insert(Member.builder()
                .id(UUID.randomUUID().toString())
                .createDate(LocalDateTime.now())
                .username(username)
                .build());
    }

    public Member update(MemberUpdateDto dto) {
        Member member = findByIdAndGet(dto.getMemberId());

        return memberRepository.save(member.toBuilder()
                .modifyDate(LocalDateTime.now())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .birthday(dto.getBirthday())
                .image(dto.getImage())
                .description(dto.getDescription())
                .build()
        );
    }

    public Member softDelete(String memberId) {
        Member member = findByIdAndGet(memberId);

        return memberRepository.save(member.toBuilder()
                .deleteDate(LocalDateTime.now())
                .build()
        );
    }

}