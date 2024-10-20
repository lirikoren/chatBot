package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.modal.ChatbotRequest;
import io.liri.chatbot.openAiChatbot.modal.ChatbotResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatbotResponseService {

    private final ChatbotAskService askChatbotService;

    public ChatbotResponseService(ChatbotAskService chatBotServiceAsk) {
        this.askChatbotService = chatBotServiceAsk;
    }

    public ChatbotResponse createChatbotResponse(ChatbotRequest chatBotRequest) {
        return new ChatbotResponse(chatBotRequest.getRequest(),
                askChatbotService.ask(chatBotRequest));
    }
}
