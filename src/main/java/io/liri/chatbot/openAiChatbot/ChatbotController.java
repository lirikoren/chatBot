package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.model.ChatbotRequest;
import io.liri.chatbot.openAiChatbot.model.ChatbotResponse;
import io.liri.chatbot.openAiChatbot.model.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("chatbotask")
@RestController
public class ChatbotController {
    private final ChatbotResponseService chatBotResponseService;
    Logger logger = LoggerFactory.getLogger(ChatbotController.class);

    public ChatbotController(ChatbotResponseService chatBotResponseService) {
        this.chatBotResponseService = chatBotResponseService;
    }

    @PreAuthorize("hasRole('MALE')")
    @PostMapping("/askmale")
    ResponseEntity<ChatbotResponse> askMaleChatbot(@RequestBody ChatbotRequest chatBotRequest) {
        var chatbotResponse = chatBotResponseService.createChatbotResponse(chatBotRequest, Gender.MALE);
        logger.info("the result is: {}", chatbotResponse);
        return ResponseEntity.ok(chatbotResponse);
    }

    @PreAuthorize("hasRole('FEMALE')")
    @PostMapping("/askfemale")
    ResponseEntity<ChatbotResponse> askFemaleChatbot(@RequestBody ChatbotRequest chatBotRequest) {
        var chatbotResponse = chatBotResponseService.createChatbotResponse(chatBotRequest, Gender.FEMALE);
        logger.info("the result is: {}", chatbotResponse);
        return ResponseEntity.ok(chatbotResponse);
    }
}

