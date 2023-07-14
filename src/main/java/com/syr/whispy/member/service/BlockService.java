package com.syr.whispy.member.service;

import com.syr.whispy.member.entity.Block;
import com.syr.whispy.member.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.syr.whispy.member.code.MemberErrorCode.ALREADY_BLOCKED;
import static com.syr.whispy.member.code.MemberErrorCode.MEMBER_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockRepository blockRepository;
    private final MemberService memberService;

    public Optional<Block> findByFromMemberIdAndBlockedMemberId(
            String fromMemberId, String toMemberId
    ) {
        return blockRepository.findByFromMemberIdAndBlockedMemberId(fromMemberId, toMemberId);
    }

    public Block create(String fromMemberId, String toMemberId) {
        if (memberService.findById(fromMemberId).isEmpty() ||
                memberService.findById(toMemberId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MEMBER_NOT_EXISTS.getMsg());
        }

        if (findByFromMemberIdAndBlockedMemberId(fromMemberId, toMemberId).isPresent()) {
            throw new DuplicateKeyException(ALREADY_BLOCKED.getMsg());
        }

        return blockRepository.insert(Block.builder()
                .id(UUID.randomUUID().toString())
                .createDate(LocalDateTime.now())
                .member(fromMemberId)
                .blockedMember(toMemberId)
                .build()
        );
    }

}
