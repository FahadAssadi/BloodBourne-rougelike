package game.positions.EnemyNest.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.ForestKeeper;
import game.actors.enemies.RedWolf;
import game.misc.Utility;
import game.weather.Weather;
import game.weather.WeatherSusceptible;

public class ForestKeeperSpawner implements Spawner, WeatherSusceptible {
    private static final int DEFAULT_SPAWN_CHANCE = 15;
    private static final int SUNNY_SPAWN_CHANCE = 30;
    private int SPAWN_CHANCE = SUNNY_SPAWN_CHANCE;

    /**
     * Spawn a new instance of the ForestKeeper enemy actor.
     *
     * @return A new instance of the HollowSolider actor.
     */
    @Override
    public Actor spawnActor() {
        return new ForestKeeper();
    }

    /**
     * Check if the spawner should spawn a ForestKeeper.
     * This method uses a random event utility to determine the outcome based on SPAWN_CHANCE.
     *
     * @return `true` if a HollowSolider should be spawned, `false` otherwise.
     */
    @Override
    public boolean doesSpawn() {
        return Utility.getRandomEventOccurs(SPAWN_CHANCE);
    }

    @Override
    public String processWeather() {
        return Weather.getWeather().getWeatherState().processWeather(this);
    }

    @Override
    public String sunnyWeather() {
        this.SPAWN_CHANCE = SUNNY_SPAWN_CHANCE;
        return "The forest keepers are becoming more active";
    }

    @Override
    public String rainyWeather() {
        this.SPAWN_CHANCE = DEFAULT_SPAWN_CHANCE;
        return "The forest keepers are becoming less active";
    }
}

