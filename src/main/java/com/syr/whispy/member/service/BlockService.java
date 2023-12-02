package com.syr.whispy.member.service;

import com.syr.whispy.base.exception.DataNotFoundException;
import com.syr.whispy.base.exception.DuplicateFieldException;
import com.syr.whispy.member.entity.Block;
import com.syr.whispy.member.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.member.code.MemberErrorCode.ALREADY_BLOCKED;
import static com.syr.whispy.member.code.MemberErrorCode.MEMBER_NOT_EXISTS;

@RequiredArgsConstructor
@Service
public class BlockService {

    private final BlockRepository blockRepository;
    private final MemberService memberService;

    public Optional<Block> findByFromMemberIdAndBlockedMemberId(
            String fromMemberId, String toMemberId
    ) {
        return blockRepository.findByMemberAndBlockedMember(fromMemberId, toMemberId);
    }

    public Block create(String fromMemberId, String toMemberId) {
        if (memberService.findById(fromMemberId).isEmpty() ||
                memberService.findById(toMemberId).isEmpty()) {
            throw new DataNotFoundException(MEMBER_NOT_EXISTS);
        }

        if (findByFromMemberIdAndBlockedMemberId(fromMemberId, toMemberId).isPresent()) {
            throw new DuplicateFieldException(ALREADY_BLOCKED);
        }

        return blockRepository.insert(Block.builder()
                .id(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .member(fromMemberId)
                .blockedMember(toMemberId)
                .build()
        );
    }

}
