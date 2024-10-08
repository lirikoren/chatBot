package io.liri.chatbot.askChatBot;

import io.liri.chatbot.askChatBot.modal.ChatBotRequest;
import io.liri.chatbot.askChatBot.modal.ChatBotResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import static org.springframework.cglib.core.ReflectUtils.getClass;

@RequestMapping("chatbotask")
@RestController
public class AskController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/ask")
    ResponseEntity<ChatBotResponse> askChatBot(@RequestBody ChatBotRequest chatBotRequest) {
        var chatBotResponse = new ChatBotResponse(chatBotRequest.getRequest(), "Roie&Liri are awsome");
        logger.info("the result is: {}", chatBotResponse);
        return ResponseEntity.ok(chatBotResponse);
    }
}

