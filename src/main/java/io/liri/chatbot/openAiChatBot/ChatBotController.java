package io.liri.chatbot.openAiChatBot;

import io.liri.chatbot.openAiChatBot.modal.ChatBotRequest;
import io.liri.chatbot.openAiChatBot.modal.ChatBotResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("chatbotask")
@RestController
public class ChatBotController {
    Logger logger = LoggerFactory.getLogger(ChatBotController.class);
    private final ChatBotResponseService chatBotResponseService;

    public ChatBotController(ChatBotResponseService chatBotResponseService) {
        this.chatBotResponseService = chatBotResponseService;
    }


    @PostMapping("/ask")
    ResponseEntity<ChatBotResponse> askChatBot(@RequestBody ChatBotRequest chatBotRequest) {
        var chatBotResponse = chatBotResponseService.createChatbotResponse(chatBotRequest);
        logger.info("the result is: {}", chatBotResponse);
        return ResponseEntity.ok(chatBotResponse);
    }
}

