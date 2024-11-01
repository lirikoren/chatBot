package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.model.ChatbotRequest;
import io.liri.chatbot.openAiChatbot.model.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.ai.chat.client.ChatClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
class ChatbotAskServiceTest {

    public static final String QUESTION = "Q";
    private static final String EXPECTED = "1234";
    private static final Gender GENDER = Gender.MALE;
    @Mock
    private WeatherChatbotClient weatherChatbotClient;

    @InjectMocks
    private ChatbotAskService askChatbotService;

    @BeforeEach
    void setUp() {
        var chatClient = mock(ChatClient.class);
        var chatClientRequestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        var callResponseSpec = mock(ChatClient.CallResponseSpec.class);
        doReturn(chatClientRequestSpec).when(chatClient).prompt();
        doReturn(chatClientRequestSpec).when(chatClientRequestSpec).user(eq(QUESTION));
        doReturn(callResponseSpec).when(chatClientRequestSpec).call();
        doReturn(EXPECTED).when(callResponseSpec).content();
        doReturn(chatClient).when(weatherChatbotClient).getChatClient(GENDER);
    }

    @Test
    void ask() {
        var chatbotRequest = new ChatbotRequest(QUESTION);
        assertEquals(EXPECTED, askChatbotService.ask(chatbotRequest,GENDER));
    }
}