package com.example.websocketblog.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChattingMessage {
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType messageType; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}
