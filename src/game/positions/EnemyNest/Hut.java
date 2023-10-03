package game.positions.EnemyNest;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.EnemyNest.spawners.Spawner;
import game.weather.Weather;
import game.weather.WeatherSusceptible;

public class Hut extends EnemyNest implements WeatherSusceptible {
    // Default display character for the Hut ground
    private final static char DEFAULT_DISPLAY_CHAR = 'h';

    private static final int RAINY_SPAWN_CHANCE = 15;
    private static final int SUNNY_SPAWN_CHANCE = 30;

    public Hut(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR, spawner);
    }

    @Override
    public void tick(Location location) {
        new Display().println(this.processWeather());
        super.tick(location);
    }

    @Override
    public String processWeather() {
        return Weather.getWeather().getWeatherState().processWeather(this);
    }

    @Override
    public String sunnyWeather() {
        spawner.setSpawnChance(SUNNY_SPAWN_CHANCE);
        return "The forest keepers are becoming more active";
    }

    @Override
    public String rainyWeather() {
        spawner.setSpawnChance(RAINY_SPAWN_CHANCE);
        return "The forest keepers are becoming less active";
    }
}
