package com.syr.whispy.member.entity;

import com.syr.whispy.base.entity.BaseEntity;
import com.syr.whispy.post.entity.Like;
import com.syr.whispy.post.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity(name = "member")
public class Member extends BaseEntity {

    @Id
    private String id;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;

    private LocalDateTime birthday;

    private String image;

    @Max(value = 50, message = "50자 이하로 작성해주세요.")
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>();

    @Builder.Default
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