package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.config.ChatResourceConfig;
import io.liri.chatbot.openAiChatbot.model.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class WeatherChatbotClientTest {

    @Mock
    private  ChatClient.Builder chatClientBuilder;

    @Mock
    private ChatResourceConfig chatResourceConfig;

    @InjectMocks
    private WeatherChatbotClient weatherChatbotClient;

    private ChatClient maleChatClient;
    private ChatClient femaleChatClient;

    @BeforeEach
    public void setUp() {
        var prompt = mock(Resource.class);
        maleChatClient = mock(ChatClient.class);
        femaleChatClient = mock(ChatClient.class);
        doReturn(prompt).when(chatResourceConfig).getMaleChatbotPrompt();
        doReturn(prompt).when(chatResourceConfig).getFemaleChatbotPrompt();
        doReturn(chatClientBuilder).when(chatClientBuilder).defaultSystem(prompt);
        doReturn(maleChatClient,femaleChatClient).when(chatClientBuilder).build();
        weatherChatbotClient.init();
    }

    @Test
    public void getChatClient() {
        assertEquals(maleChatClient, weatherChatbotClient.getChatClient(Gender.MALE));
        assertEquals(femaleChatClient, weatherChatbotClient.getChatClient(Gender.FEMALE));
    }

    @Test
    public void setChatClient() {
        ChatClient newChatClient = mock(ChatClient.class);
        weatherChatbotClient.setChatClientMap(Gender.MALE, newChatClient);
        assertEquals(newChatClient, weatherChatbotClient.getChatClient(Gender.MALE));
    }
}
