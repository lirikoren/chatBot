package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.weather.WeatherFetcherService;
import io.liri.chatbot.weather.model.output.Coord;
import io.liri.chatbot.weather.model.output.Main;
import io.liri.chatbot.weather.model.output.Weather;
import io.liri.chatbot.weather.model.output.WeatherData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ChatClientDataLoaderTest {
    @Mock
    SimpleVectorStore vectorStore;
    @Mock
    WeatherFetcherService weatherFetcherService;
    @Mock
    WeatherData weatherData;

    @InjectMocks
    ChatClientDataLoader chatClientDataLoader;

    @BeforeEach
    void setUp() {
        chatClientDataLoader = new ChatClientDataLoader(vectorStore, weatherFetcherService);
    }

    @Test
    void loadWeatherVectorStore() {
//        Coord coord = new Coord("1","1");
//        Main main = new Main("25.00","25.00","30.00","10%");
//        Weather weather = new Weather("sunny");
//        var weatherData = new WeatherData(coord,main, weather,"Herzlia");
//        List<Document> result =  List.of(new Document());
//        doReturn(result).when(weatherFetcherService).fetchWeatherData();
//        assertEquals(vectorStore, chatClientDataLoader.loadWeatherVectorStore());
    }
}
