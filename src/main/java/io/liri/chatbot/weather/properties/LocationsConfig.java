package io.liri.chatbot.weather.properties;

import io.liri.chatbot.weather.model.input.Location;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties("chatbot.geo")
public record LocationsConfig(
        Set<Location> locations,
        String url,
        String apiKey) {
}
