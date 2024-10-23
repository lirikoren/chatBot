package io.liri.chatbot.weather;

import io.liri.chatbot.weather.model.output.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(WeatherController.class);


    public WeatherController(WeatherFetcherService weatherFetcherService) {
        this.weatherFetcherService = weatherFetcherService;
    }

    @GetMapping
    public ResponseEntity<Set<WeatherData>> getWeather() {
        logger.info("the weather is: {}", weatherFetcherService.fetchWeatherData());
        return ok(weatherFetcherService.fetchWeatherData());
    }
}
