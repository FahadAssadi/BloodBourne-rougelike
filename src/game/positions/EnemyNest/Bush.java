package game.positions.EnemyNest;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.EnemyNest.spawners.Spawner;
import game.weather.Weather;
import game.weather.WeatherSusceptible;

public class Bush extends EnemyNest implements WeatherSusceptible {
    private static final char DEFAULT_DISPLAY_CHAR = 'm';

    private static final int SUNNY_SPAWN_CHANCE = 30;
    private static final int RAINY_SPAWN_CHANCE = 45;

    public Bush(Spawner spawner) {
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
        return "The red wolves are becoming less active";
    }

    @Override
    public String rainyWeather() {
        spawner.setSpawnChance(RAINY_SPAWN_CHANCE);
        return "The red wolves are becoming more active";
    }
}
