package game.positions.EnemyNest;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.EnemyNest.spawners.Spawner;
import game.weather.Weather;
import game.weather.WeatherSusceptible;

public class Hut extends EnemyNest implements WeatherSusceptible {
    // Default display character for the Hut ground
    private final static char DEFAULT_DISPLAY_CHAR = 'h';

    public Hut(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR, spawner);
    }

    @Override
    public String processWeather() {
        return Weather.getWeather().getWeatherState().processWeather(this);
    }

    @Override
    public String sunnyWeather() {
        return null;
    }

    @Override
    public String rainyWeather() {
        return null;
    }
}
