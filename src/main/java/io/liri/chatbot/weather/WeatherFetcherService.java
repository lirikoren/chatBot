package io.liri.chatbot.weather;

import io.liri.chatbot.weather.properties.LocationsConfig;
import io.liri.chatbot.weather.model.input.Location;
import io.liri.chatbot.weather.model.output.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class WeatherFetcherService {


    private static final String UNITS = "units";
    private static final String METRIC = "metric";
    private static final String LON = "lon";
    private static final String LAT = "lat";
    private static final String APPID = "appid";
    private final RestClient restClient;
    private final LocationsConfig locationsConfig;
    Logger logger = LoggerFactory.getLogger(WeatherFetcherService.class);

    public WeatherFetcherService(@Qualifier("defaultRestClient") RestClient restClient, LocationsConfig locationsConfig) {
        this.restClient = restClient;
        this.locationsConfig = locationsConfig;
    }

    public Set<WeatherData> fetchWeatherData() {
        var weathers = locationsConfig.locations()
                .stream()
                .map(this::getWeatherDataForLocation)
                .collect(toSet());
        logger.info("weathers: {}", weathers);
        return weathers;
    }

    private WeatherData getWeatherDataForLocation(Location location) {
        return restClient
                .get()
                .uri(locationsConfig.url() + "?lat={lat}&lon={lon}&appid={appid}&units={units}", Map.of(LAT, location.lat(), LON, location.lng(), APPID, locationsConfig.apiKey(), UNITS, METRIC))
                .retrieve()
                .body(WeatherData.class);
    }
}


