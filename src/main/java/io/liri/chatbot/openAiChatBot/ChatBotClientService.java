package io.liri.chatbot.openAiChatBot;

import io.liri.chatbot.openAiChatBot.modal.ChatBotRequest;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

// the service main role is to communicate with chatgpt API to send user question and receive responses.

@Service
public class ChatBotClientService {

    private final ChatClient.Builder chatClientBuilder;
    Logger logger = LoggerFactory.getLogger(getClass());
    private ChatClient chatClient;

    /**
     * @noinspection SpringJavaInjectionPointsAutowiringInspection
     */
    public ChatBotClientService(ChatClient.Builder chatClientBuilder) {
        this.chatClientBuilder = chatClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.chatClient = chatClientBuilder.build();
    }

    public String ask(ChatBotRequest chatBotRequest) {
        return chatClient
                .prompt()
                .user(chatBotRequest.getRequest())
                .call()
                .content();
    }

}
