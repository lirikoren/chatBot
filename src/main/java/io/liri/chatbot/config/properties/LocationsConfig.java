package io.liri.chatbot.config.properties;

import io.liri.chatbot.weather.model.Location;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties("chatbot.geo")
public record LocationsConfig(
        Set<Location> locations,
        String url) {
}
