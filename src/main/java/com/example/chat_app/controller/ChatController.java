package com.example.chat_app.controller;
import com.example.chat_app.Model.ChatMessage;
import com.example.chat_app.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ChatController {
    @Autowired private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired private ChatService chatService;

    @GetMapping("/getAllChats/{username}")
    public ResponseEntity<Map<String, List<ChatMessage>>> getAllChats(@PathVariable String username) {
        try {
            Map<String, List<ChatMessage>> userMessages = chatService.getAllChats(username);
            return new ResponseEntity<>(userMessages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @MessageMapping("/message")
    public ChatMessage processMessage(@Payload ChatMessage chatMessage) {
        chatService.saveMessage(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiver(),"/private",chatMessage);
        return chatMessage;
    }

    @GetMapping("/getAllMessages")
    public List<ChatMessage> getAllMessages() {
          return chatService.getAllMessages();
    }

    @DeleteMapping("/deleteChats")
    public String deleteAllChats() {
        chatService.deleteAllChats();
//        jdbcTemplate.execute("DROP DATABASE defaultdb");
        return "Delete chats successfully";
    }

}
