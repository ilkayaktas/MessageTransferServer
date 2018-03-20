package edu.ilkayaktas.healthnetwork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created by ilkayaktas on 18.03.2018 at 00:16.
 */

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
         * we register a websocket endpoint that the clients will use to connect to our websocket server
         */
        registry.addEndpoint("/example-endpoint", "/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         * we’re configuring a message broker that will be used to route messages from one client to another.
         */

        /*
         * in-memory message broker to carry the messages back to the client on destinations prefixed with "/topic", "/queue", "/exchange"
         * defines that the messages whose destination starts with "/topic", "/queue", "/exchange" should be routed to the message broker.
         * message broker broadcasts messages to all the connected clients who are subscribed to a particular topic.
         */
        registry.enableSimpleBroker("/topic", "/queue", "/exchange");

        /*
         * defines that the messages whose destination starts with "/topic", "/queue", "/app" prefix should be routed to message-handling methods (@MessageMapping-annotated methods)
         * this prefix will be used to define all the message mappings; for example, "/app/hello"
         */
        registry.setApplicationDestinationPrefixes("/topic", "/queue", "/app"); // prefix in client queries
        registry.setUserDestinationPrefix("/user");

    }
}
