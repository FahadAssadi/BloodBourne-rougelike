package game.positions.EnemyNest.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.ForestKeeper;
import game.misc.Utility;
import game.weather.WeatherSusceptible;

public class ForestKeeperSpawner implements Spawner, WeatherSusceptible {
    private static final int SPAWN_CHANCE = 15;

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
