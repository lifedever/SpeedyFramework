package com.kanasinfo.web.controller;

import com.kanasinfo.model.Greeting;
import com.kanasinfo.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by gefangshuai on 2017/5/29.
 */
@Controller
@MessageMapping("/hello")
public class GreetingController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping
    @SendTo(value = "/topic/greetings")
    public Greeting greeting(HelloMessage message, Principal principal) throws Exception {
        return new Greeting(String.format("%s say: %s.", message.getName(), message.getMessage()));
    }

    @MessageMapping("/user")
    @SendToUser(value = "/topic/greetings", broadcast = false)
    public Greeting greetingUser(HelloMessage message, Principal principal) throws Exception {
        return new Greeting(String.format("%s say: %s.", message.getName(), message.getMessage()));
    }

    @MessageMapping("/user/{baz}")
    public void processMessageFromClient(HelloMessage message, @DestinationVariable String baz) throws Exception {
        simpMessagingTemplate.convertAndSend("/topic/reply/" + baz, new Greeting(String.format("%s say: %s.", message.getName(), message.getMessage())));
    }
}
