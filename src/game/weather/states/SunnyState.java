package game.weather.states;

import edu.monash.fit2099.engine.displays.Display;
import game.weather.SunnySusceptible;
import game.weather.Weather;

public class SunnyState implements WeatherState {
    @Override
    public void processWeatherState() {
        for (SunnySusceptible sunnySusceptible: Weather.getSunnySusceptibles()) {
            new Display().println(sunnySusceptible.sunnyWeather());

        }
    }

    @Override
    public String toString() {
        return "sunny";
    }
}
