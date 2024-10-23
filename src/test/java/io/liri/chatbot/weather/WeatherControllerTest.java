package io.liri.chatbot.weather;

import io.liri.chatbot.weather.model.output.WeatherData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.ok;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    WeatherFetcherService weatherFetcherService;
    @Mock
    WeatherData weatherData;
    @InjectMocks
    WeatherController weatherController;


    @BeforeEach
    void setUp() {
    }

    @Test
    void getWeather() {

        Set<WeatherData> weatherDataSet = Collections.singleton(weatherData);
        doReturn(weatherDataSet).when(weatherFetcherService).fetchWeatherData();
        assertEquals(ok(weatherDataSet), weatherController.getWeather());
    }


}