package io.liri.chatbot.openAiChatBot;

import io.liri.chatbot.openAiChatBot.modal.ChatBotRequest;
import io.liri.chatbot.openAiChatBot.modal.ChatBotResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatBotResponseService {

    private final ChatBotClientService chatBotClientService;

    public ChatBotResponseService(ChatBotClientService chatBotClientService) {
        this.chatBotClientService = chatBotClientService;
    }

    public ChatBotResponse createChatbotResponse(ChatBotRequest chatBotRequest) {
        return new ChatBotResponse(chatBotRequest.getRequest(),
                chatBotClientService.ask(chatBotRequest));
    }
}
