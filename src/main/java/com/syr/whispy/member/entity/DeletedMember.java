package com.syr.whispy.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
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
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "deleted_member")
public class DeletedMember {

    @Id
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
