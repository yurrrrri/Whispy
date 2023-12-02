package com.syr.whispy.chat.repository;

import com.syr.whispy.chat.entity.Chat;
import com.syr.whispy.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, String> {

    List<Message> findByChat(Chat chat);
}
