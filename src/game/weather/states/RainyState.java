package game.weather.states;

import edu.monash.fit2099.engine.displays.Display;
import game.weather.RainySusceptible;
import game.weather.WeatherSusceptiblesManager;


public class RainyState implements WeatherState {
    @Override
    public void processWeatherState() {
        for (RainySusceptible rainySusceptible: WeatherSusceptiblesManager.getRainySusceptibles()) {
            new Display().println(rainySusceptible.rainyWeather());

        }
    }

    @Override
    public String toString() {
        return "rainy";
    }
}
