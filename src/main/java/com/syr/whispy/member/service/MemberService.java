package com.syr.whispy.member.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.member.dto.MemberUpdateDto;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.syr.whispy.member.code.MemberErrorCode.MEMBER_NOT_EXISTS;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Member findByIdAndGet(Long id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException(MEMBER_NOT_EXISTS));
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findByUsernameAndGet(String username) {
        return findByUsername(username).orElseGet(() -> join(username));
    }

    @Transactional
    public Member join(String username) {
        return findByUsername(username)
                .orElseGet(() -> memberRepository.save(Member.builder()
                        .username(username)
                        .createdDate(LocalDateTime.now())
                        .build()));
    }

    @Transactional
    public Member update(MemberUpdateDto dto) {
        Member member = findByIdAndGet(dto.getMemberId());

        return memberRepository.save(member.toBuilder()
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .birthday(dto.getBirthday())
                .image(dto.getImage())
                .description(dto.getDescription())
                .modifiedDate(LocalDateTime.now())
                .build()
        );
    }

    @Transactional
    public Member softDelete(Long id) {
        Member member = findByIdAndGet(id);

        return memberRepository.save(member.toBuilder()
                .deletedDate(LocalDateTime.now())
                .build()
        );
    }
}