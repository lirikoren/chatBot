package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.modal.ChatbotRequest;
import io.liri.chatbot.openAiChatbot.modal.ChatbotResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("chatbotask")
@RestController
public class ChatbotController {
    Logger logger = LoggerFactory.getLogger(ChatbotController.class);
    private final ChatbotResponseService chatBotResponseService;

    public ChatbotController(ChatbotResponseService chatBotResponseService) {
        this.chatBotResponseService = chatBotResponseService;
    }


    @PostMapping("/ask")
    ResponseEntity<ChatbotResponse> askChatbot(@RequestBody ChatbotRequest chatBotRequest) {
        var chatbotResponse = chatBotResponseService.createChatbotResponse(chatBotRequest);
        logger.info("the result is: {}", chatbotResponse);
        return ResponseEntity.ok(chatbotResponse);
    }
}

