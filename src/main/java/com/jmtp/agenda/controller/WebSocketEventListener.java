package com.jmtp.agenda.controller;

import com.jmtp.agenda.model.ChatMessage;
import com.jmtp.agenda.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public class WebSocketEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectedEvent event){
        LOGGER.info("Nueva coneccion establecida");
    }

    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event){
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());


        final String username = (String)headerAccessor.getSessionAttributes().get("username");
        ChatMessage chatMessage = ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();

        sendingOperations.convertAndSend("ws-topic/public", chatMessage);
    }

}
