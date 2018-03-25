package edu.ilkayaktas.healthnetwork.controller;

import edu.ilkayaktas.healthnetwork.model.EchoModel;
import edu.ilkayaktas.healthnetwork.model.FooService;
import edu.ilkayaktas.healthnetwork.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aselsan on 20.03.2018 at 10:45.
 */
@RestController
public class SocketController {

    @Autowired
    FooService fooService;

    @Autowired
    SocketService socketService;

    @MessageMapping("/hello-msg-mapping")
    @SendTo("/topic/greetings") // All subscribers to the “/topic/greetings” destination will receive the message.
    EchoModel echoMessageMapping(String message) {
        System.out.println("React to hello-msg-mapping");
        return new EchoModel(message.trim()+"wqeqweqeq");
    }

    @RequestMapping(value = "/hello-convert-and-send", method = RequestMethod.POST)
    void echoConvertAndSend(@RequestParam("msg") String message) {
        System.out.println("React to hello-convert-and-send");
        socketService.echoMessage(message);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public String addUser(@Payload String chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage);
        return chatMessage;
    }
}
