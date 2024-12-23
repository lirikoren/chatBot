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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ChatbotResponseServiceTest {

    private static final String ANSWER = "Answer";
    private static final String QUESTION = "Question";
    public static final Gender GENDER = Gender.MALE;
    @Mock
    ChatbotAskService askChatbotService;

    @InjectMocks
    ChatbotResponseService chatbotResponseService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createChatbotResponse() {
        ChatbotRequest chatbotRequest = new ChatbotRequest(QUESTION);
        ChatbotResponse chatbotResponse = new ChatbotResponse(QUESTION, ANSWER);

        doReturn(ANSWER).when(askChatbotService).ask(eq(chatbotRequest),eq(GENDER));
        assertEquals(chatbotResponse, chatbotResponseService.createChatbotResponse(chatbotRequest, GENDER));
    }
}