package game.positions.EnemyNest;

import game.positions.EnemyNest.spawners.Spawner;
import game.weather.WeatherSusceptible;

public class Hut extends EnemyNest  implements WeatherSusceptible {
    // Default display character for the Hut ground
    private final static char DEFAULT_DISPLAY_CHAR = 'h';

    public Hut(Spawner spawner) {
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
