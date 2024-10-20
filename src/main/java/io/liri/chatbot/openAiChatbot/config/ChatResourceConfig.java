package io.liri.chatbot.openAiChatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ChatResourceConfig {

    @Value("classpath:/chatbot/system-prompt.txt")
    private Resource chatbotPrompt;


    public Resource getChatbotPrompt() {
        return chatbotPrompt;
    }
}
