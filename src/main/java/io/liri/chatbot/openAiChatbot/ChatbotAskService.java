package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.model.ChatbotRequest;
import io.liri.chatbot.openAiChatbot.model.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// the service main role is to communicate with chatgpt API to send user question and receive responses.

@Service
public class ChatbotAskService {

    private final WeatherChatbotClient weatherChatbotClient;
    Logger logger = LoggerFactory.getLogger(ChatbotAskService.class);

    public ChatbotAskService(WeatherChatbotClient weatherChatbotClient) {
        this.weatherChatbotClient = weatherChatbotClient;
    }


    public String ask(ChatbotRequest chatbotRequest, Gender gender) {
        logger.info("asked:{}", chatbotRequest.getRequest());
        return weatherChatbotClient.getChatClient(gender)
                .prompt()
                .user(chatbotRequest.getRequest())
                .call()
                .content();
    }

}
