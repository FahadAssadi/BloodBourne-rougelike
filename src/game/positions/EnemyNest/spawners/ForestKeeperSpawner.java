package game.positions.EnemyNest.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.ForestKeeper;
import game.misc.Utility;

/**
 * A spawner for creating instances of the ForestKeeper enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 */
public class ForestKeeperSpawner implements Spawner {
    // The chance of spawning a ForestKeeper (out of 100)
    private static final int DEFAULT_SPAWN_CHANCE = 15;
    private int spawnChance = DEFAULT_SPAWN_CHANCE;

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
        return Utility.getRandomEventOccurs(spawnChance);
    }

    /**
     * Setter for the spawner's spawn chance to allow possible modification.
     * (eg. due to weather effects)
     */
    public void setSpawnChance(int spawnChance) {
        this.spawnChance = spawnChance;
    }
}

