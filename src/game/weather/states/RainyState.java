package game.weather.states;

import game.weather.WeatherSusceptiblesManager;

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
