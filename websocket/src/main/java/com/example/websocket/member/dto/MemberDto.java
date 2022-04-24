package com.example.websocket.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
