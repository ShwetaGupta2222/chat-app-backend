package com.example.chat_app.repository;
import com.example.chat_app.Model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySender(String sender);
    List<ChatMessage> findByReceiver(String receiver);
}