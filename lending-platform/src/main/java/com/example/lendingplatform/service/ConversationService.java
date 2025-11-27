package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Conversation;
import com.example.lendingplatform.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public List<Conversation> getByUser(Long userId) {
        return conversationRepository.findByUserId(userId);
    }

    public Conversation create(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public Conversation getById(Long id) {
        return conversationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));
    }
}