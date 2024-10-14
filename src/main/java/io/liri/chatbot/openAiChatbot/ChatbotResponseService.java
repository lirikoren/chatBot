package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.modal.ChatbotRequest;
import io.liri.chatbot.openAiChatbot.modal.ChatbotResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatbotResponseService {

    private final ChatbotClientService chatbotClientService;

    public ChatbotResponseService(ChatbotClientService chatBotClientService) {
        this.chatbotClientService = chatBotClientService;
    }

    public ChatbotResponse createChatbotResponse(ChatbotRequest chatBotRequest) {
        return new ChatbotResponse(chatBotRequest.getRequest(),
                chatbotClientService.ask(chatBotRequest));
    }
}
