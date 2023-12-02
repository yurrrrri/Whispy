package com.syr.whispy.member.service;

import com.syr.whispy.base.exception.DuplicateFieldException;
import com.syr.whispy.member.entity.Block;
import com.syr.whispy.member.entity.Member;
import com.syr.whispy.member.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.syr.whispy.member.code.MemberErrorCode.ALREADY_BLOCKED;

@RequiredArgsConstructor
@Transactional
@Service
public class BlockService {

    private final BlockRepository blockRepository;

    @Transactional(readOnly = true)
    public Optional<Block> findByFromMemberAndBlockedMember(
            Member fromMember, Member toMember
    ) {
        return blockRepository.findByMemberAndBlockedMember(fromMember, toMember);
    }

    public Block create(Member fromMember, Member toMember) {
        if (findByFromMemberAndBlockedMember(fromMember, toMember).isPresent()) {
            throw new DuplicateFieldException(ALREADY_BLOCKED);
        }

        return blockRepository.save(Block.builder()
                .member(fromMember)
                .blockedMember(toMember)
                .createdDate(LocalDateTime.now())
                .build()
        );
    }
}
