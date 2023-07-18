package com.syr.whispy.chat.entity;

import com.syr.whispy.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Document(collection = "chat")
public class Chat extends BaseEntity {

    private List<String> members;

}
