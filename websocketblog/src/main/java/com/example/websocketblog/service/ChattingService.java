package com.example.websocketblog.service;

import com.example.websocketblog.domain.ChattingRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChattingService {

    private final ObjectMapper objectMapper;
    private Map<String, ChattingRoom> chattingRooms;

    @PostConstruct
    private void init() {
        chattingRooms = new LinkedHashMap<>();
    }

    public List<ChattingRoom> findAllRoom() {
        return new ArrayList<>(chattingRooms.values());
    }

    public ChattingRoom findRoomById(String roomId) {
        return chattingRooms.get(roomId);
    }

    public ChattingRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChattingRoom chatRoom = ChattingRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chattingRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}