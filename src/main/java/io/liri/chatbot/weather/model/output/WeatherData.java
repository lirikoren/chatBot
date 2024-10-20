package io.liri.chatbot.weather.model.output;

import java.util.Set;

public record WeatherData(Coord coord, Main main, Set<Weather> weather, String name) {

}
