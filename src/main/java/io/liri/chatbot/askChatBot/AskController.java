package io.liri.chatbot.askChatBot;

import io.liri.chatbot.askChatBot.modal.ChatBotRequest;
import io.liri.chatbot.askChatBot.modal.ChatBotResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("chatbotask")
@RestController
public class AskController {

    @PostMapping("/ask")
    ResponseEntity<ChatBotResponse> askChatBot(@RequestBody ChatBotRequest chatBotRequest) {
        var chatBotResponse = new ChatBotResponse(chatBotRequest.getRequest(), "Roie&Liri are awsome");
        return ResponseEntity.ok(chatBotResponse);
    }
}

