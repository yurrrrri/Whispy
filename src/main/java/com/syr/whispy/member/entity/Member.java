package com.syr.whispy.member.entity;

import com.syr.whispy.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Document(collection = "member")
public class Member extends BaseEntity {

    private String username;

    private String password;

    private String nickname;

    private String email;

    private LocalDateTime birthday;

    private String image;

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