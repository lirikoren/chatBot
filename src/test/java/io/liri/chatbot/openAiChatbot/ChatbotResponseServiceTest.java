package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.modal.ChatbotRequest;
import io.liri.chatbot.openAiChatbot.modal.ChatbotResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ChatbotResponseServiceTest {

    private static final String ANSWER = "Answer";
    private static final String QUESTION = "Question";
    private static ChatbotRequest chatbotRequest;
    private static ChatbotResponse chatbotResponse;
    @Mock
    ChatbotAskService askChatbotService;

    @InjectMocks
    ChatbotResponseService chatbotResponseService;

    @BeforeEach
    void setUp() {
        chatbotRequest = new ChatbotRequest(QUESTION);
        chatbotResponse = new ChatbotResponse(QUESTION, ANSWER);
    }

    @Test
    void createChatbotResponse() {

        doReturn(ANSWER).when(askChatbotService).ask(eq(chatbotRequest));
        ChatbotResponse response = chatbotResponseService.createChatbotResponse(chatbotRequest);
        assertEquals(chatbotResponse, response);
    }
}