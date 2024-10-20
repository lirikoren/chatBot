package io.liri.chatbot.weather;

import io.liri.chatbot.weather.model.output.WeatherData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("weather")
public class WeatherController {

    private final WeatherFetcherService weatherFetcherService;

    public WeatherController(WeatherFetcherService weatherFetcherService) {
        this.weatherFetcherService = weatherFetcherService;
    }

    @GetMapping
    public ResponseEntity<Set<WeatherData>> getWeather() {
        return ok(weatherFetcherService.fetchWeatherData());
    }
}
