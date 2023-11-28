package com.syr.whispy.member.entity;

import com.syr.whispy.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Member extends BaseEntity {

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String email;

    private LocalDateTime birthday;

    private String image;

    @Max(50)
    private String description; // 소개글

    private List<String> starredPosts;

    private Set<Role> authorities = new HashSet<>();

    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(i -> new SimpleGrantedAuthority("ROLE_" + i.name()))
                .toList();
    }

    public void addRole(Role role) {
        this.authorities.add(role);
    }

}