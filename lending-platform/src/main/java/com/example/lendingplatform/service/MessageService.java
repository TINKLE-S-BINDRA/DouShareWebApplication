package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Message;
import com.example.lendingplatform.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getMessagesByConversation(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }

    public Message send(Message message) {
        return messageRepository.save(message);
    }

    public Message markRead(Long id) {
        return messageRepository.findById(id).map(msg -> {
            msg.setRead(true);
            return messageRepository.save(msg);
        }).orElseThrow(() -> new RuntimeException("Message not found"));
    }
}
