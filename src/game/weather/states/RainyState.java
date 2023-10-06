package game.weather.states;

import game.weather.weathermanager.WeatherSusceptiblesManager;

public class RainyState implements WeatherState {
    @Override
    public void processWeatherState(WeatherSusceptiblesManager weatherSusceptiblesManager) {
        weatherSusceptiblesManager.processAllRainySusceptible();
    }

    @Override
    public String toString() {
        return "rainy";
    }
}
