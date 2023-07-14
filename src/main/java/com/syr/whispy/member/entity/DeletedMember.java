package com.syr.whispy.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Document(collection = "deleted_member")
public class DeletedMember {

    @Id
    private String id;

    private String username;

    private String nickname;

    private String email;

    private LocalDateTime birthday;

    private LocalDateTime createDate;

}
