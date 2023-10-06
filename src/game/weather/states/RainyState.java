package game.weather.states;

import game.weather.WeatherSusceptiblesManager;

public class RainyState implements WeatherState {
    @Override
    public void processWeatherState() {
        WeatherSusceptiblesManager.getWeatherSusceptiblesManager().processAllRainySusceptible();
    }

    @Override
    public String toString() {
        return "rainy";
    }
}
