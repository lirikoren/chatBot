package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.modal.ChatbotRequest;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

// the service main role is to communicate with chatgpt API to send user question and receive responses.

@Service
public class ChatbotClientService {

    private final ChatClient.Builder chatClientBuilder;
    Logger logger = LoggerFactory.getLogger(getClass());
    private ChatClient chatClient;

    /**
     * @noinspection SpringJavaInjectionPointsAutowiringInspection
     */
    public ChatbotClientService(ChatClient.Builder chatClientBuilder) {
        this.chatClientBuilder = chatClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.chatClient = chatClientBuilder.build();
    }

    public String ask(ChatbotRequest chatbotRequest) {
        return chatClient
                .prompt()
                .user(chatbotRequest.getRequest())
                .call()
                .content();
    }

}
