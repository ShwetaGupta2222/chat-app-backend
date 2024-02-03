package com.example.chat_app.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class DeleteMessages {
    private String deletedBy;
    private String deletedTo;
    private List<String> deleteChats;

    public List<String> getDeleteChats() {
        return deleteChats;
    }
    public void setDeleteChats(List<String> deleteChats) {
        List<String> tempDeleteChats =  new ArrayList<>();
        for(String deleteChat : deleteChats){
            tempDeleteChats.add(deleteChat);
        }
        this.deleteChats = tempDeleteChats;
    }
}
