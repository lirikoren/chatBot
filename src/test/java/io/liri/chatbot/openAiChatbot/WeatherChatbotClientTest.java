package io.liri.chatbot.openAiChatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class WeatherChatbotClientTest {

    @Mock
    private ChatClient chatClient;

    @InjectMocks
    private WeatherChatbotClient weatherChatbotClient;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(weatherChatbotClient, "chatClient", chatClient);
    }

    @Test
    public void getChatClient() {
        assertEquals(chatClient, weatherChatbotClient.getChatClient());
    }

    @Test
    public void setChatClient() {
        ChatClient newChatClient = mock(ChatClient.class);
        weatherChatbotClient.setChatClient(newChatClient);
        assertEquals(newChatClient, weatherChatbotClient.getChatClient());
    }
}
