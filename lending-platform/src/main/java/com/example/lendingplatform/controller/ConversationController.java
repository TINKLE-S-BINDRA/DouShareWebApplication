package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.Conversation;
import com.example.lendingplatform.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    @Autowired
    private ConversationRepository conversationRepository;

    // List all conversations for the current user
    @GetMapping
    public List<Conversation> getAllConversations(@RequestParam Long userId) {
        return conversationRepository.findByUserId(userId);
    }

    // Create a new conversation
    @PostMapping
    public ResponseEntity<Conversation> createConversation(@RequestBody Conversation conversation) {
        Conversation savedConversation = conversationRepository.save(conversation);
        return ResponseEntity.ok(savedConversation);
    }

    // Get a specific conversation
    @GetMapping("/{id}")
    public ResponseEntity<Conversation> getConversation(@PathVariable Long id) {
        return conversationRepository.findById(id)
                .map(conversation -> ResponseEntity.ok(conversation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}