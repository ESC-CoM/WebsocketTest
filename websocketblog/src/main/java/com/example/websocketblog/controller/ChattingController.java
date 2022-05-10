package com.example.websocketblog.controller;

import com.example.websocketblog.domain.ChattingRoom;
import com.example.websocketblog.service.ChattingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChattingController {

    private final ChattingService chattingService;

    public ChattingController(ChattingService chattingService) {
        this.chattingService = chattingService;
    }

    @PostMapping
    public ChattingRoom createRoom(@RequestBody String name) {
        return chattingService.createRoom(name);
    }

    @GetMapping
    public List<ChattingRoom> findAllRoom() {
        return chattingService.findAllRoom();
    }
}
