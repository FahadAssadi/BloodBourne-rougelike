package game.weather.states;

import game.weather.WeatherSusceptiblesManager;

public interface WeatherState {
    void processWeatherState(WeatherSusceptiblesManager weatherSusceptiblesManager);
    String toString();
}
