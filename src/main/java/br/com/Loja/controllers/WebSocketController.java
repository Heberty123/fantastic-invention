package br.com.Loja.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/message")
    @SendTo("/topic/stock")
    public String processMessageFromClient(@Payload String message) throws Exception {
        System.out.println(message);
        return "accept!";
    }
}
