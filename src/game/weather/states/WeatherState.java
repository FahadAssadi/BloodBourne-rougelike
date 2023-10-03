package game.weather.states;

import game.weather.WeatherSusceptible;

public interface WeatherState {
    String processWeather(WeatherSusceptible weatherSusceptible);

    String toString();
}
