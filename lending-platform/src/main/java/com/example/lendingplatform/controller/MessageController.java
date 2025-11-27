package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.Conversation;
import com.example.lendingplatform.model.Message;
import com.example.lendingplatform.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations/{conversationId}/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // Get all messages in a conversation
    @GetMapping
    public List<Message> getAllMessages(@PathVariable Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }

    // Send a new message
    @PostMapping
    public ResponseEntity<Message> sendMessage(@PathVariable Long conversationId, @RequestBody Message message) {
        message.setConversation(new Conversation(conversationId)); // Set the conversation ID
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }

    // Mark message as read
    @PatchMapping("/{id}/read")
    public ResponseEntity<Message> markAsRead(@PathVariable Long id) {
        return messageRepository.findById(id)
                .map(message -> {
                    message.setRead(true);
                    messageRepository.save(message);
                    return ResponseEntity.ok(message);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}