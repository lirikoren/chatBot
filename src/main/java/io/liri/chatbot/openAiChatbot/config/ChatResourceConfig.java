package io.liri.chatbot.openAiChatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ChatResourceConfig {

    @Value("classpath:/chatbot/male-system-prompt.txt")
    private Resource maleChatbotPrompt;


    @Value("classpath:/chatbot/female-system-prompt.txt")
    private Resource femaleChatbotPrompt;


    public Resource getMaleChatbotPrompt() {
        return maleChatbotPrompt;
    }

    public Resource getFemaleChatbotPrompt() {
        return femaleChatbotPrompt;
    }
}
