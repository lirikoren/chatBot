package io.liri.chatbot.weather;

import io.liri.chatbot.weather.properties.LocationsConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class WeatherFetcherServiceTest {
    @Mock
    RestClient restClient;
    @Mock
    LocationsConfig locationsConfig;

    @InjectMocks
    WeatherFetcherService weatherFetcherService;

    @BeforeEach
    void setUp() {
        weatherFetcherService = new WeatherFetcherService(restClient, locationsConfig);
    }

    @Test
    void fetchWeatherData() {

    }
}