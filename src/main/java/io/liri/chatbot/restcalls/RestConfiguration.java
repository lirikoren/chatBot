package io.liri.chatbot.restcalls;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfiguration {


    @Bean
    public RestClient defaultRestClient() {
        return RestClient.create();
    }
}
