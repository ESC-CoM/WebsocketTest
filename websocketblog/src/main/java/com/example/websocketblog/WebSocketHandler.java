package com.example.websocketblog;

import com.example.websocketblog.domain.ChattingMessage;
import com.example.websocketblog.domain.ChattingRoom;
import com.example.websocketblog.service.ChattingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChattingService chattingService;

    public WebSocketHandler(ObjectMapper objectMapper, ChattingService chattingService) {
        this.objectMapper = objectMapper;
        this.chattingService = chattingService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        String payload = textMessage.getPayload();
        log.info("payload {}", payload);

        ChattingMessage chattingMessage = objectMapper.readValue(payload, ChattingMessage.class);
        ChattingRoom chattingRoom = chattingService.findRoomById(chattingMessage.getRoomId());
        chattingRoom.handleActions(session, chattingMessage, chattingService);
    }
}