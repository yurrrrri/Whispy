package com.syr.whispy.chat.repository;

import com.syr.whispy.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ChatRepository extends JpaRepository<Chat, String> {
}
