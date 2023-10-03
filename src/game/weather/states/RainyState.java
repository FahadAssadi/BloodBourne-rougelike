package game.weather.states;

import game.weather.WeatherSusceptible;

public class RainyState implements WeatherState {
    @Override
    public String processWeather(WeatherSusceptible weatherSusceptible) {
        return weatherSusceptible.rainyWeather();
    }

    @Override
    public String toString() {
        return "rainy";
    }
}
