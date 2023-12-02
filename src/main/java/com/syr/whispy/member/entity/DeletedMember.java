package com.syr.whispy.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DeletedMember {

    @Id
    @GeneratedValue
    private String id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;

    private LocalDateTime birthday;

    @CreatedDate
    private LocalDateTime createdDate;
}
