package game.positions.enemynests;

import game.positions.enemynests.spawners.Spawner;
import game.weather.RainySusceptible;
import game.weather.SunnySusceptible;

public class Hut extends EnemyNest implements SunnySusceptible, RainySusceptible {
    // Default display character for the Hut ground
    private final static char DEFAULT_DISPLAY_CHAR = 'h';

    private static final int RAINY_SPAWN_CHANCE = 15;
    private static final int SUNNY_SPAWN_CHANCE = 30;

    public Hut(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR, spawner);
        registerAsRainySusceptible();
        registerAsSunnySusceptible();
    }

    @Override
    public String sunnyWeather() {
        spawner.setSpawnChance(SUNNY_SPAWN_CHANCE);
        return "The number of Forest Keepers is starting to increase!";
    }

    @Override
    public String rainyWeather() {
        spawner.setSpawnChance(RAINY_SPAWN_CHANCE);
        return "The number of Forest Keepers is starting to dwindle!";
    }
}
