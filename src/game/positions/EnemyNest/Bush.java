package game.positions.EnemyNest;

import edu.monash.fit2099.engine.positions.Location;
import game.positions.EnemyNest.spawners.Spawner;
import game.weather.WeatherSusceptible;

public class Bush extends EnemyNest implements WeatherSusceptible {
    private static final char DEFAULT_DISPLAY_CHAR = 'm';

    public Bush(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR, spawner);
    }


    @Override
    public void tick(Location location) {

    }

    @Override
    public void sunnyWeather() {

    }


    @Override
    public void rainyWeather() {

    }
}
