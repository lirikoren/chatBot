package io.liri.chatbot.scheduler;

import io.liri.chatbot.openAiChatbot.ChatClientDataLoader;
import io.liri.chatbot.openAiChatbot.WeatherChatbotClient;
import io.liri.chatbot.openAiChatbot.model.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ChatBotDataReloadService {

    private final WeatherChatbotClient weatherChatbotClient;
    private final ChatClientDataLoader chatClientDataLoader;
    Logger logger = LoggerFactory.getLogger(ChatBotDataReloadService.class);

    public ChatBotDataReloadService(WeatherChatbotClient weatherChatbotClient, ChatClientDataLoader chatClientDataLoader) {
        this.weatherChatbotClient = weatherChatbotClient;
        this.chatClientDataLoader = chatClientDataLoader;
    }

    @Scheduled(fixedDelayString = "${app.schedule.reload-interval}", initialDelay = 0L)
    @Async
    public void reloadChatClient() {
        var vectorStore = chatClientDataLoader.loadWeatherVectorStore();
        Arrays.stream(Gender.values()).forEach(gender -> {
            ChatClient chatClient = mutateWeatherData(vectorStore, gender);
            weatherChatbotClient.setChatClientMap(gender, chatClient);
        });
        logger.info("the client has been reloaded");
    }

    private ChatClient mutateWeatherData(SimpleVectorStore vectorStore, Gender gender) {
        return weatherChatbotClient.getChatClient(gender)
                .mutate()
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
    }


}
