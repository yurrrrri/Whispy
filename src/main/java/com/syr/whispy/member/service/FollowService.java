package com.syr.whispy.member.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.base.exception.DuplicateFieldException;
import com.syr.whispy.member.entity.Follow;
import com.syr.whispy.member.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.member.code.MemberErrorCode.ALREADY_FOLLOWED;
import static com.syr.whispy.member.code.MemberErrorCode.MEMBER_NOT_EXISTS;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberService memberService;

    public Optional<Follow> findByFromMemberIdAndFollowedMemberId(
            String fromMemberId, String toMemberId
    ) {
        return followRepository.findByFromMemberIdAndFollowedMemberId(fromMemberId, toMemberId);
    }

    public Follow create(String fromMemberId, String toMemberId) {
        if (memberService.findById(fromMemberId).isEmpty() ||
                memberService.findById(toMemberId).isEmpty()) {
            throw new DataNotFoundException(MEMBER_NOT_EXISTS);
        }

        if (findByFromMemberIdAndFollowedMemberId(fromMemberId, toMemberId).isPresent()) {
            throw new DuplicateFieldException(ALREADY_FOLLOWED);
        }

        return followRepository.insert(Follow.builder()
                .id(UUID.randomUUID().toString())
                .createDate(LocalDateTime.now())
                .member(fromMemberId)
                .followedMember(toMemberId)
                .build()
        );
    }

}
