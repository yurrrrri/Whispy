package com.syr.whispy.member.service;

import com.syr.whispy.base.exception.DuplicateFieldException;
import com.syr.whispy.member.entity.Follow;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.member.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.syr.whispy.member.code.MemberErrorCode.ALREADY_FOLLOWED;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FollowService {

    private final FollowRepository followRepository;

    public Optional<Follow> findByFromMemberAndFollowedMember(Member fromMember, Member toMember) {
        return followRepository.findByMemberAndFollowedMember(fromMember, toMember);
    }

    public List<Follow> findByFromMember(Member member) {
        return followRepository.findByMember(member);
    }

    @Transactional
    public Follow create(Member fromMember, Member toMember) {
        if (findByFromMemberAndFollowedMember(fromMember, toMember).isPresent()) {
            throw new DuplicateFieldException(ALREADY_FOLLOWED);
        }

        return followRepository.save(Follow.builder()
                .member(fromMember)
                .followedMember(toMember)
                .createdDate(LocalDateTime.now())
                .build()
        );
    }
}
