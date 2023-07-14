package com.syr.whispy.member.service;

import com.syr.whispy.member.entity.Follow;
import com.syr.whispy.member.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.member.code.MemberErrorCode.ALREADY_FOLLOWED;
import static com.syr.whispy.member.code.MemberErrorCode.MEMBER_NOT_EXISTS;

@Service
@RequiredArgsConstructor
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MEMBER_NOT_EXISTS.getMsg());
        }

        if (findByFromMemberIdAndFollowedMemberId(fromMemberId, toMemberId).isPresent()) {
            throw new DuplicateKeyException(ALREADY_FOLLOWED.getMsg());
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
