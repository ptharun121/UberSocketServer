package com.example.ubersocketserver.controllers;

import com.example.ubersocketserver.dto.ChatRequest;
import com.example.ubersocketserver.dto.ChatResponse;
import com.example.ubersocketserver.dto.TestRequest;
import com.example.ubersocketserver.dto.TestResponse;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public TestController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public TestResponse pingCheck(TestRequest message) {
        System.out.println("recieved message from client: " + message.getData());
        return new TestResponse("Recieved");
    }

    @MessageMapping("/chat/{room}")
    @SendTo("/topic/message/{room}")
    public ChatResponse chatMessage(@DestinationVariable String room, ChatRequest request) {
        System.out.println("##### " + request);
        return ChatResponse.builder().
                name(request.getName())
                .message(request.getMessage())
                .timeStamp(String.valueOf(System.currentTimeMillis()))
                .build();
    }

    @MessageMapping("/privateChat/{room}/{userId}")
    public void privateChatMessage(@DestinationVariable String room, @DestinationVariable String userId, ChatRequest request) {
        ChatResponse response = ChatResponse.builder().
                name(request.getName())
                .message(request.getMessage())
                .timeStamp(String.valueOf(System.currentTimeMillis()))
                .build();
        simpMessagingTemplate.convertAndSendToUser(  userId,"/queue/privateMessage/", response);
    }


}
