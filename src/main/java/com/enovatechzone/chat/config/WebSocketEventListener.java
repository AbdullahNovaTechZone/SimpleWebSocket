package com.enovatechzone.chat.config;

import com.enovatechzone.chat.dto.MessageDTO;
import com.enovatechzone.chat.dto.Type;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        String username = String.valueOf(stompHeaderAccessor.getSessionAttributes().get("username"));

        if (username != null) {
            log.info("User Disconnected: {}", username);
            MessageDTO messageDTO = MessageDTO.builder()
                    .type(Type.LEFT)
                    .sender(username)
                    .build();

            messageSendingOperations.convertAndSend("/topic/public", messageDTO);
        }
    }
}
