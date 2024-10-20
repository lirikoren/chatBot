package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.modal.ChatbotRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ChatbotAskServiceTest {

    private static final String EXPECTED = "1234";
    @Mock
    private WeatherChatbotClient weatherChatbotClient;

    @InjectMocks
    private ChatbotAskService askChatbotService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void ask() {
        var chatbotRequest = new ChatbotRequest("Q");
        assertEquals(EXPECTED, askChatbotService.ask(chatbotRequest));
    }
}