package io.liri.chatbot.askChatBot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// the service main role is to communicate with chatgpt API to send user question and receive responses.

@Service
public class AskService {

    private final String URL = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(getClass());

    //DI by constructor
    public AskService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }



}
