package game.weather.states;

import edu.monash.fit2099.engine.displays.Display;
import game.weather.SunnySusceptible;
import game.weather.WeatherSusceptiblesManager;

public class SunnyState implements WeatherState {
    @Override
    public void processWeatherState() {
        for (SunnySusceptible sunnySusceptible: WeatherSusceptiblesManager.getSunnySusceptibles()) {
            new Display().println(sunnySusceptible.sunnyWeather());

        }
    }

    @Override
    public String toString() {
        return "sunny";
    }
}
