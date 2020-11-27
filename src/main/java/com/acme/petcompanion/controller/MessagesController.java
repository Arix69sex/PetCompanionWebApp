package com.acme.petcompanion.controller;

import com.acme.petcompanion.domain.model.Message;
import com.acme.petcompanion.domain.service.MessageService;
import com.acme.petcompanion.resource.MessageResource;
import com.acme.petcompanion.resource.SaveMessageResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MessagesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageService messageService;

    @Operation(summary = "Get Reviews By UserId", description = "Get Reviews By UserId", tags = {"reviews"})
    @GetMapping("users/{userId}/messages")
    public Page<MessageResource> getAllMessagesByUserId(
            @PathVariable Long userId, Pageable pageable) {
        Page<Message> reviewPage = messageService.getAllMessagesByUserId(userId, pageable);
        List<MessageResource> resources = reviewPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Review", description = "Create a new Review", tags = {"messages"})
    @PostMapping("users/{userId}/services/{serviceId}/reviews")
    public MessageResource createMessage(
            @PathVariable Long userId,
            @PathVariable Long serviceId,
            @Valid @RequestBody SaveMessageResource resource) {
        return convertToResource(messageService.createMessage(userId, serviceId, convertToEntity(resource)));
    }

    @Operation(summary = "delete Message", description = "delete Message with given Id", tags = {"messages"})
    @DeleteMapping("chats/{chatId}/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(
            @PathVariable(value = "chatId") Long chatId,
            @PathVariable(value = "messageId") Long messageId) {
        return messageService.deleteMessage(chatId, messageId);
    }

    private Message convertToEntity(SaveMessageResource resource) {
        return mapper.map(resource, Message.class);
    }

    private MessageResource convertToResource(Message entity) {
        return mapper.map(entity, MessageResource.class);
    }
}
