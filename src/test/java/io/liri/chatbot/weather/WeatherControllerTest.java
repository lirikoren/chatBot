package io.liri.chatbot.weather;

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

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.ResponseEntity.ok;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    WeatherFetcherService weatherFetcherService;

    @InjectMocks
    WeatherController weatherController;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getWeather() {

        Set<WeatherData> weatherDataSet = Collections.singleton(buildWeatherData());
        doReturn(weatherDataSet).when(weatherFetcherService).fetchWeatherData();
        assertEquals(ok(weatherDataSet), weatherController.getWeather());
    }

    private WeatherData buildWeatherData() {
        Coord coord = new Coord("1", "1");
        Main main = new Main("25.00", "25.00", "30.00", "10%");
        Weather weather = new Weather("sunny");
        return new WeatherData(coord, main, Set.of(weather), "RAANANA");
    }


}