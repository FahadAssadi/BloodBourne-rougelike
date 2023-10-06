package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.RedWolf;
import game.misc.Utility;

/**
 * A spawner for creating instances of the RedWolf enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 */
public class RedWolfSpawner implements Spawner {
    // The chance of spawning a RedWolf (out of 100)
    private static final int DEFAULT_SPAWN_CHANCE = 30;
    private int spawnChance = DEFAULT_SPAWN_CHANCE;

    /**
     * Spawn a new instance of the RedWolf enemy actor.
     *
     * @return A new instance of the RedWolf actor.
     */
    @Override
    public Actor spawnActor() {
        return new RedWolf();
    }

    /**
     * Check if the spawner should spawn a RedWolf.
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
    @Override
    public void setSpawnChance(int spawnChance) {
        this.spawnChance = spawnChance;
    }
}
