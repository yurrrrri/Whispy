package com.syr.whispy.member.service;

import com.syr.whispy.base.exception.DuplicateFieldException;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.syr.whispy.member.code.MemberErrorCode.USERNAME_ALREADY_EXISTS;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member join(String username, String password) {
        if (findByUsername(username).isPresent()) {
            throw new DuplicateFieldException(USERNAME_ALREADY_EXISTS);
        }

        password = passwordEncoder.encode(password);

        Member member = Member.builder()
                .username(username)
                .password(password)
                .build();
        memberRepository.save(member);

        return member;
    }

}