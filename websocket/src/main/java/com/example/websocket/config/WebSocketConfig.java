package com.example.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker //message broker가 지원하는 웹소켓 메시지 핸들링을 가능하게 한다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //simple memory-based message broker가 greeting 메시지를 /pub이 접두어로 붙은 목적지에 전달한다.
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //endpoint란 서비스에서 제공하는 커뮤니케이션 채널의 한쪽 끝, 즉 요청을 받아 응답을 제공하는 서비스를 사용할 수 있는 지점을 의미한다.(= API가 리소스에 접근할 수 있도록 해주는 URI)
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }
}
