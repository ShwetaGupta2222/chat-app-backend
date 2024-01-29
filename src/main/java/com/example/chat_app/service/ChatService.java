package com.example.chat_app.service;
import com.example.chat_app.Model.ChatMessage;
import com.example.chat_app.Model.User;
import com.example.chat_app.repository.ChatRepository;
import com.example.chat_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;

    public Map<String, List<ChatMessage>> getAllChats(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Map<String, List<ChatMessage>> userMessages = new HashMap<>();
            List<ChatMessage> sentMessages = chatRepository.findBySender(username);
            List<ChatMessage> receivedMessages = chatRepository.findByReceiver(username);
            for (ChatMessage message : sentMessages) {
                userMessages.putIfAbsent(message.getReceiver(), new ArrayList<>());
                userMessages.get(message.getReceiver()).add(message);
            }
            for (ChatMessage message : receivedMessages) {
                userMessages.putIfAbsent(message.getSender(), new ArrayList<>());
                userMessages.get(message.getSender()).add(message);
            }
            return userMessages ;
        } else {
            throw new Error("User not found");
        }
    }
    public void saveMessage(ChatMessage chatMessage){
        chatRepository.save(chatMessage);
    }

}
