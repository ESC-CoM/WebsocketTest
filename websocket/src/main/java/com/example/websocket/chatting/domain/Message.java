package com.example.websocket.chatting.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String name;

    public Message() {
    }

    public Message(String name) {
        this.name = name;
    }
}
