package io.liri.chatbot.kafka.consumer;

import io.liri.chatbot.register.controller.RegisterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class MessageConsumer {

    Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "chatbot", groupId = "my-group-id")
    public void listen(String message) {
        logger.info("Received message : {}" , message);
    }
}
