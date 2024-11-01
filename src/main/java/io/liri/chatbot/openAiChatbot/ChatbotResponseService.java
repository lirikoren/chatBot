package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.model.ChatbotRequest;
import io.liri.chatbot.openAiChatbot.model.ChatbotResponse;
import io.liri.chatbot.openAiChatbot.model.Gender;
import org.springframework.stereotype.Service;

@Service
public class ChatbotResponseService {

    private final ChatbotAskService askChatbotService;

    public ChatbotResponseService(ChatbotAskService chatBotServiceAsk) {
        this.askChatbotService = chatBotServiceAsk;
    }

    public ChatbotResponse createChatbotResponse(ChatbotRequest chatBotRequest, Gender gender) {
        return new ChatbotResponse(chatBotRequest.getRequest(),
                askChatbotService.ask(chatBotRequest,gender));
    }
}
