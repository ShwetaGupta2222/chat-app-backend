package com.example.chat_app.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class ChatMessage {
    @Id
    private String timestamp;
    private String sender;
    private String senderGender ;
    private String receiver;
    private String receiverGender;
    private String message;

}
