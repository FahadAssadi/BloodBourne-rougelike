package game.weather2.states;

import game.weather2.WeatherSusceptible;

public class RainyState implements WeatherState {
    @Override
    public String processWeather(WeatherSusceptible weatherSusceptible) {
        return weatherSusceptible.rainyWeather();
    }
}
