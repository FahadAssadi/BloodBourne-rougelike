package game.weather.states;

import game.weather.weathermanager.WeatherSusceptiblesManager;

public interface WeatherState {
    void processWeatherState(WeatherSusceptiblesManager weatherSusceptiblesManager);
    String toString();
}
