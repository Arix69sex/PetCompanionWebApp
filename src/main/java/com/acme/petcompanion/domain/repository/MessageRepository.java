package com.acme.petcompanion.domain.repository;

import com.acme.petcompanion.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findAllByUserId(Long userId, Pageable pageable);

    Page<Message> findAllByChatId(Long chatId, Pageable pageable);

    Page<Message> findAllByChatIdAndUserId(Long userId, Long chatId, Pageable pageable);
}
