package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.modal.ChatbotRequest;
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


    public String ask(ChatbotRequest chatbotRequest) {
        logger.info("asked:{}", chatbotRequest.getRequest());
        return weatherChatbotClient.getChatClient()
                .prompt()
                .user(chatbotRequest.getRequest())
                .call()
                .content();
    }

}
