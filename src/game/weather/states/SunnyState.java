package game.weather.states;

import game.weather.WeatherSusceptible;

public class SunnyState implements WeatherState {
    @Override
    public String processWeather(WeatherSusceptible weatherSusceptible) {
        return weatherSusceptible.sunnyWeather();
    }

    @Override
    public String toString() {
        return "sunny";
    }
}
