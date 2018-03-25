package edu.ilkayaktas.healthnetwork.service;

import edu.ilkayaktas.healthnetwork.model.others.EchoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by aselsan on 20.03.2018 at 09:25.
 */

@Service
public class SocketService {
    @Autowired
    private SimpMessagingTemplate simpTemplate;

    public void echoMessage(String message) {
        System.out.println("Start echoMessage ${new Date()}");
        simpTemplate.convertAndSend("/topic/greetings", new EchoModel(message+"asasdadas"));
        System.out.println("End echoMessage ${new Date()}");
    }
}
