package com.acme.petcompanion.domain.service;

import com.acme.petcompanion.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    Page<Message> getAllMessagesByChatId(Long chatId, Pageable pageable);

    Page<Message> getAllMessagesByUserId(Long userId, Pageable pageable);

    Page<Message> getAllMessagesByChatIdAndUserId(Long userId, Long chatId, Pageable pageable);

    Message createMessage(Long chatId, Long messageId,Message message);

    ResponseEntity<?> deleteMessage(Long chatId, Long messageId);
}
