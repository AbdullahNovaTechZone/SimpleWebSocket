package com.enovatechzone.chat.controller;

import com.enovatechzone.chat.dto.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageDTO sendMessage(@Payload MessageDTO messageDTO) {
        return messageDTO;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public MessageDTO addUser(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
        simpMessageHeaderAccessor.getSessionAttributes().put("username", messageDTO.getSender());
        return messageDTO;
    }
}
