package io.liri.chatbot.weather.model;

import java.util.Set;

public record WeatherData(Coord coord, Main main, Set<Weather> weather) {

}
