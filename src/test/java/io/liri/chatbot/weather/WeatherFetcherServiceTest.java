package io.liri.chatbot.weather;

import io.liri.chatbot.weather.model.input.Location;
import io.liri.chatbot.weather.model.output.Coord;
import io.liri.chatbot.weather.model.output.Main;
import io.liri.chatbot.weather.model.output.Weather;
import io.liri.chatbot.weather.model.output.WeatherData;
import io.liri.chatbot.weather.properties.LocationsConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class WeatherFetcherServiceTest {
    public static final String PETCH_TIKVA = "Petch-tikva";
    private static final String KFAR_SABA = "Kfar-Saba";
    @Mock
    RestClient restClient;
    @Mock
    LocationsConfig locationsConfig;

    @InjectMocks
    WeatherFetcherService weatherFetcherService;

    @BeforeEach
    void setUp() {
        var requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        var responseSpec = mock(RestClient.ResponseSpec.class);
        doReturn(requestHeadersUriSpec).when(restClient).get();
        doReturn(requestHeadersUriSpec).when(requestHeadersUriSpec).uri(anyString(), anyMap());
        doReturn(responseSpec).when(requestHeadersUriSpec).retrieve();
        doReturn(buildWeatherData(KFAR_SABA), buildWeatherData(PETCH_TIKVA)).when(responseSpec).body(WeatherData.class);
        doReturn("baseUrl").when(locationsConfig).url();
        doReturn("key").when(locationsConfig).apiKey();
    }

    @Test
    void fetchWeatherData() {
        doReturn(Set.of(new Location("lat", "lng", "name"))).when(locationsConfig).locations();
        assertEquals(Set.of(buildWeatherData(KFAR_SABA)), weatherFetcherService.fetchWeatherData());
    }

    @Test
    void fetchWeatherDataMultipleLocations() {
        doReturn(Set.of(new Location("lat", "lng", "name"),
                new Location("lat2", "lng2", "name2"))).when(locationsConfig).locations();
        assertEquals(Set.of(buildWeatherData(PETCH_TIKVA), buildWeatherData(KFAR_SABA)), weatherFetcherService.fetchWeatherData());
    }

    private WeatherData buildWeatherData(String city) {
        Coord coord = new Coord("1", "1");
        Main main = new Main("25.00", "25.00", "30.00", "10%");
        Weather weather = new Weather("sunny");
        return new WeatherData(coord, main, Set.of(weather), city);
    }
}