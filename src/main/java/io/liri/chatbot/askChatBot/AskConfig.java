package io.liri.chatbot.askChatBot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
// http client - provided by spring boot
@Configuration
public class AskConfig {
    // singltone
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
