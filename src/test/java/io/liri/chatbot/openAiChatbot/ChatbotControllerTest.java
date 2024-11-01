package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.model.ChatbotRequest;
import io.liri.chatbot.openAiChatbot.model.ChatbotResponse;
import io.liri.chatbot.openAiChatbot.model.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ChatbotControllerTest {

    private static final String ANSWER = "Answer";
    private static final String QUESTION = "Question";

    @Mock
    ChatbotResponseService chatbotResponseService;

    @InjectMocks
    ChatbotController chatbotController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void askMaleChatBot() {
        var chatbotRequest = new ChatbotRequest("1234");
        var chatbotResponse = new ChatbotResponse(QUESTION, ANSWER);

        doReturn(chatbotResponse).when(chatbotResponseService).createChatbotResponse(eq(chatbotRequest), eq(Gender.MALE));

        ResponseEntity<ChatbotResponse> response = chatbotController.askMaleChatbot(chatbotRequest);

        assertEquals(ResponseEntity.ok(chatbotResponse), response);

    }

    @Test
    void askFemaleChatBot() {
        var chatbotRequest = new ChatbotRequest("1234");
        var chatbotResponse = new ChatbotResponse(QUESTION, ANSWER);

        doReturn(chatbotResponse).when(chatbotResponseService).createChatbotResponse(eq(chatbotRequest), eq(Gender.FEMALE));

        ResponseEntity<ChatbotResponse> response = chatbotController.askFemaleChatbot(chatbotRequest);

        assertEquals(ResponseEntity.ok(chatbotResponse), response);

    }

}