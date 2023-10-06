package game.weather.states;

import game.weather.WeatherSusceptiblesManager;

public class SunnyState implements WeatherState {
    @Override
    public void processWeatherState(WeatherSusceptiblesManager weatherSusceptiblesManager) {
        weatherSusceptiblesManager.processAllSunnySusceptible();
    }

    @Override
    public String toString() {
        return "sunny";
    }
}
