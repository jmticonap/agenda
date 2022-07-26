package com.jmtp.agenda.controller;

import com.jmtp.agenda.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("chat.send")
    @SendTo("/ws-topic/public")
    public ChatMessage sendMessage(@Payload final ChatMessage chatMessage){
        return chatMessage;
    }

    @MessageMapping("chat.newUser")
    @SendTo("/ws-topic/public")
    public ChatMessage  newUser(@Payload final ChatMessage chatMessage,
                                SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
