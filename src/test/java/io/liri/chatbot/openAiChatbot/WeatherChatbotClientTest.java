package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.config.ChatResourceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class WeatherChatbotClientTest {


    private static final String EXPECTED = "1234";

    @Mock
    private ChatClient.Builder chatClientBuilder;
    @Mock
    private ChatResourceConfig chatResourceConfig;
    @Mock
    private ChatClient chatClient;

    @InjectMocks
    private WeatherChatbotClient weatherChatbotClient;

    @BeforeEach
    public void setUp() {
        weatherChatbotClient = new WeatherChatbotClient(chatClientBuilder, chatResourceConfig);
    }

    @Test
    public void initAndGet() {
        weatherChatbotClient.init();
        assertEquals(chatClient, weatherChatbotClient.getChatClient());

    }

    @Test
    public void setAndGetChatClient() {
        ChatClient newChatClient = mock(ChatClient.class);
        weatherChatbotClient.setChatClient(newChatClient);
        assertEquals(newChatClient, weatherChatbotClient.getChatClient());
    }
}
