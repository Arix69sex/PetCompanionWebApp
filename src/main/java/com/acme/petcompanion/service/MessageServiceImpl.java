package com.acme.petcompanion.service;

import com.acme.petcompanion.domain.model.Message;
import com.acme.petcompanion.domain.repository.ChatRepository;
import com.acme.petcompanion.domain.repository.MessageRepository;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.MessageService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Message> getAllMessagesByChatId(Long chatId, Pageable pageable) {
        return messageRepository.findAllByChatId(chatId,pageable);
    }

    @Override
    public Page<Message> getAllMessagesByChatIdAndUserId(Long userId, Long chatId, Pageable pageable) {
        return messageRepository.findAllByChatIdAndUserId(userId, chatId, pageable);
    }

    @Override
    public Page<Message> getAllMessagesByUserId(Long userId, Pageable pageable) {
        return messageRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Message createMessage(Long chatId, Long messageId,Message message) {
        return chatRepository.findById(chatId).map(chat -> {
            message.setChat(chat);
            return messageRepository.save(message);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Chat", "Id", chatId));

    }

    @Override
    public ResponseEntity<?> deleteMessage(Long chatId, Long messageId) {
        if (!chatRepository.existsById(chatId))
            throw new ResourceNotFoundException("Chat", "Id", chatId);
        return messageRepository.findById(messageId).map(message -> {
            messageRepository.delete(message);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Message", "Id", messageId));
    }
}
