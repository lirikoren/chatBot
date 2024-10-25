package io.liri.chatbot.scheduler;

import io.liri.chatbot.openAiChatbot.ChatClientDataLoader;
import io.liri.chatbot.openAiChatbot.WeatherChatbotClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SimpleVectorStore;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ChatBotDataReloadServiceTest {
    @Mock
    WeatherChatbotClient weatherChatbotClient;
    @Mock
    ChatClientDataLoader chatClientDataLoader;

    @InjectMocks
    ChatBotDataReloadService chatBotDataReloadService;

    ChatClient chatClient = mock(ChatClient.class);

    @BeforeEach
    void setUp() {

        var builder = mock(ChatClient.Builder.class);
        var vectorStore = mock(SimpleVectorStore.class);
        doReturn(vectorStore).when(chatClientDataLoader).loadWeatherVectorStore();
        doReturn(chatClient).when(weatherChatbotClient).getChatClient();
        doReturn(builder).when(chatClient).mutate();
        doReturn(builder).when(builder).defaultAdvisors(any(QuestionAnswerAdvisor.class));
        doReturn(chatClient).when(builder).build();
    }

    @Test
    void reloadChatClient() {
        chatBotDataReloadService.reloadChatClient();
        verify(weatherChatbotClient).setChatClient(eq(chatClient));
    }
}