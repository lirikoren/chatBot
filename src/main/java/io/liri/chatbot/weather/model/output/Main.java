package io.liri.chatbot.weather.model.output;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Main(String temp, String feelsLike, String tempMax, String humidity) {
}
