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

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ChatBotDataReloadServiceTest {
    @Mock
    WeatherChatbotClient weatherChatbotClient;
    @Mock
    ChatClientDataLoader chatClientDataLoader;
    @Mock
    ChatClient chatClient;


    @InjectMocks
    ChatBotDataReloadService chatBotDataReloadService;

    @BeforeEach
    void setUp() {
        chatBotDataReloadService = new ChatBotDataReloadService(weatherChatbotClient, chatClientDataLoader);
    }

    @Test
    void reloadChatClient() {
        //not working

        var vectorStore = chatClientDataLoader.loadWeatherVectorStore();
        doReturn(chatClient)
                .when(weatherChatbotClient)
                .getChatClient()
                .mutate()
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
        weatherChatbotClient.setChatClient(chatClient);
        chatBotDataReloadService.reloadChatClient();
        verify(chatClientDataLoader).loadWeatherVectorStore();
        verify(weatherChatbotClient).setChatClient(chatClient);
    }
}