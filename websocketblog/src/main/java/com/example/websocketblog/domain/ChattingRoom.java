package com.example.websocketblog.domain;

import com.example.websocketblog.service.ChattingService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChattingRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChattingRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public <T> void sendMessage(T message, ChattingService chattingService) {
        sessions.parallelStream().forEach(session -> chattingService.sendMessage(session, message));
    }

    public void handleActions(WebSocketSession session, ChattingMessage chattingMessage, ChattingService chattingService) {
        if (chattingMessage.getMessageType().equals(ChattingMessage.MessageType.ENTER)) {
            sessions.add(session);
            chattingMessage.setMessage(chattingMessage.getSender()+"님이 입장했습니다.");
        }
        sendMessage(chattingMessage, chattingService);
    }
}
