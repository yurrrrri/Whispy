package com.syr.whispy.chat.entity;

import com.syr.whispy.base.entity.BaseEntity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Chat extends BaseEntity {

    @ManyToOne
    private List<String> members;
}
