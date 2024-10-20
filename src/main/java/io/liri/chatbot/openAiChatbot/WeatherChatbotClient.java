package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.config.ChatResourceConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class WeatherChatbotClient {


    private final ChatClient.Builder chatClientBuilder;
    private final ChatResourceConfig chatResourceConfig;
    private ChatClient chatClient;


    public WeatherChatbotClient(ChatClient.Builder chatClientBuilder,
                                ChatResourceConfig chatResourceConfig) {
        this.chatClientBuilder = chatClientBuilder;
        this.chatResourceConfig = chatResourceConfig;
    }

    @PostConstruct
    public void init() {
        this.chatClient = chatClientBuilder
                .defaultSystem(chatResourceConfig.getChatbotPrompt())
                .build();
    }

    public ChatClient getChatClient() {
        return chatClient;
    }


    public void setChatClient(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
}
