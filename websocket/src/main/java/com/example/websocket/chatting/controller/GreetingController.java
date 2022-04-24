package com.example.websocket.chatting.controller;

import com.example.websocket.chatting.domain.Greeting;
import com.example.websocket.chatting.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    /**
     * 메시지가 /hello로 전송되면 greeting() 메서드가 호출
     * @param message 메시지의 payload가 Message에 바인딩되어 Greeting으로 넘겨진다.
     * @return Greeting 객체를 생성한 후 @SendTo에 정의된 것과 같이 /pub/greeting 구독자(sub)에게 전송된다.
     * 메시지의 name은 클라이언트의 DOM에서 리랜더링되기 때문에 삭제된다.
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {

        Thread.sleep(1000); // 클라이언트가 메시지를 보낸 후 서버가 메시지를 비동기식으로 처리해야하는 만큼 시간이 걸릴 수 있음을 보여줌
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @GetMapping("/chat")
    public String startchat() {
        return "chatting/index";
    }
}