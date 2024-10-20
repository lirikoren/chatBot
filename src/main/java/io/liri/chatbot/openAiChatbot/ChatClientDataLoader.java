package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.weather.WeatherFetcherService;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatClientDataLoader {

    private final SimpleVectorStore vectorStore;
    private final WeatherFetcherService weatherFetcherService;

    public ChatClientDataLoader(SimpleVectorStore vectorStore, WeatherFetcherService weatherFetcherService) {
        this.vectorStore = vectorStore;
        this.weatherFetcherService = weatherFetcherService;
    }

    public SimpleVectorStore loadWeatherVectorStore() {
        vectorStore.add(createDocumentList());
        return vectorStore;
    }

    private List<Document> createDocumentList() {
        return weatherFetcherService.fetchWeatherData()
                .stream()
                .map(weatherData -> new Document(weatherData.name(), weatherData.toString(), null, Map.of("City", weatherData.name())))
                .toList();
    }
}
