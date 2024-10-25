package io.liri.chatbot.openAiChatbot;

import io.liri.chatbot.weather.WeatherFetcherService;
import io.liri.chatbot.weather.model.output.Coord;
import io.liri.chatbot.weather.model.output.Main;
import io.liri.chatbot.weather.model.output.Weather;
import io.liri.chatbot.weather.model.output.WeatherData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;

import java.util.List;
import java.util.Set;

import static io.liri.chatbot.openAiChatbot.ChatClientDataLoader.CITY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChatClientDataLoaderTest {
    public static final String HERZLIA = "Herzlia";
    @Mock
    SimpleVectorStore vectorStore;
    @Mock
    WeatherFetcherService weatherFetcherService;

    @InjectMocks
    ChatClientDataLoader chatClientDataLoader;

    @Captor
    ArgumentCaptor<List<Document>> documentsCaptor;


    @BeforeEach
    void setUp() {
        var weatherData = buildWeatherData();
        Set<WeatherData> weatherDataSet = Set.of(weatherData);
        doReturn(weatherDataSet).when(weatherFetcherService).fetchWeatherData();
    }

    @Test
    void loadWeatherVectorStore() {
        chatClientDataLoader.loadWeatherVectorStore();
        verify(vectorStore).add(documentsCaptor.capture());
        assertEquals(1, documentsCaptor.getValue().size());
        assertEquals(HERZLIA, documentsCaptor.getValue().get(0).getMetadata().get(CITY));
    }

    private WeatherData buildWeatherData() {
        Coord coord = new Coord("1", "1");
        Main main = new Main("25.00", "25.00", "30.00", "10%");
        Weather weather = new Weather("sunny");
        return new WeatherData(coord, main, Set.of(weather), HERZLIA);
    }
}
