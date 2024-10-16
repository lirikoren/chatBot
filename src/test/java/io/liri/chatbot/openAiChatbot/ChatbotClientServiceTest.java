package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.openAiChatbot.modal.ChatbotRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ChatbotClientServiceTest {

    private static final String EXPECTED = "1234";
    @Mock
    private ChatClient.Builder chatClientBuilder;

    @InjectMocks
    private ChatbotClientService chatbotClientService;

    @BeforeEach
    void setUp() {
        ChatClient chatClient = initChatClient();
        doReturn(chatClient).when(chatClientBuilder).build();
        chatbotClientService.init();
    }

    @Test
    void ask() {
        var chatbotRequest = new ChatbotRequest("Q");
        assertEquals(EXPECTED, chatbotClientService.ask(chatbotRequest));
    }

    private ChatClient initChatClient() {
        var chatClient = mock(ChatClient.class);
        var chatClientRequestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        var callResponseSpec = mock(ChatClient.CallResponseSpec.class);
        doReturn(chatClientRequestSpec).when(chatClient).prompt();
        doReturn(chatClientRequestSpec).when(chatClientRequestSpec).user(anyString());
        doReturn(callResponseSpec).when(chatClientRequestSpec).call();
        doReturn(EXPECTED).when(callResponseSpec).content();
        return chatClient;
    }
}