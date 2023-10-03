package game.weather2.states;

import game.weather2.WeatherSusceptible;

public interface WeatherState {
    String processWeather(WeatherSusceptible weatherSusceptible);
}
