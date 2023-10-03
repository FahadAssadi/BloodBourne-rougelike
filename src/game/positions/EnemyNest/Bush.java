package game.positions.EnemyNest;

import game.positions.EnemyNest.spawners.Spawner;
import game.weather.WeatherSusceptible;

public class Bush extends EnemyNest implements WeatherSusceptible {
    private static final char DEFAULT_DISPLAY_CHAR = 'm';

    public Bush(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR, spawner);
    }

    @Override
    public String processWeather() {
        return null;
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
