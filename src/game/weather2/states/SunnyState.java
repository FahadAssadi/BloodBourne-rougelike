package game.weather2.states;

import game.weather2.WeatherSusceptible;

public class SunnyState implements WeatherState {
    @Override
    public String processWeather(WeatherSusceptible weatherSusceptible) {
        return weatherSusceptible.sunnyWeather();
    }
}
