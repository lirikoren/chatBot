package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.config.ChatResourceConfig;
import io.liri.chatbot.openAiChatbot.model.Gender;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.liri.chatbot.openAiChatbot.model.Gender.FEMALE;
import static io.liri.chatbot.openAiChatbot.model.Gender.MALE;

@Component
@EnableScheduling
public class WeatherChatbotClient {


    private final ChatClient.Builder chatClientBuilderMale;
    private final ChatClient.Builder chatClientBuilderFemale;
    private final ChatResourceConfig chatResourceConfig;
    private Map<Gender, ChatClient> chatClientMap;


    public WeatherChatbotClient(ChatClient.Builder chatClientBuilderMale,
                                ChatClient.Builder chatClientBuilderFemale,
                                ChatResourceConfig chatResourceConfig) {
        this.chatClientBuilderMale = chatClientBuilderMale;
        this.chatClientBuilderFemale = chatClientBuilderFemale;
        this.chatResourceConfig = chatResourceConfig;
    }

    @PostConstruct
    public void init() {
        chatClientMap = new ConcurrentHashMap<>();
        chatClientMap.put(MALE, chatClientBuilderMale
                .defaultSystem(chatResourceConfig.getMaleChatbotPrompt())
                .build());
        chatClientMap.put(FEMALE, chatClientBuilderFemale
                .defaultSystem(chatResourceConfig.getFemaleChatbotPrompt())
                .build());
    }

    public ChatClient getChatClient(Gender gender) {
        return chatClientMap.get(gender);
    }


    public void setChatClientMap(Gender gender, ChatClient chatClient) {
        chatClientMap.put(gender, chatClient);
    }
}
