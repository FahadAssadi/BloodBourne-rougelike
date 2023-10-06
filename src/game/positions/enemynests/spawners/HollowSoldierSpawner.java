package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.HollowSoldier;
import game.misc.Utility;

/**
 * A spawner for creating instances of the HollowSoldier enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 * Created By:
 * @author Fahad Assadi
 */
public class HollowSoldierSpawner implements Spawner{
    // The chance of spawning a HollowSoldier (out of 100)
    private static final int DEFAULT_SPAWN_CHANCE = 10;
    private int spawnChance = DEFAULT_SPAWN_CHANCE;

    /**
     * Spawn a new instance of the HollowSoldier enemy actor.
     *
     * @return A new instance of the HollowSoldier actor.
     */
    @Override
    public Actor spawnActor() {
        return new HollowSoldier();
    }

    /**
     * Check if the spawner should spawn a HollowSoldier.
     * This method uses a random event utility to determine the outcome based on SPAWN_CHANCE.
     *
     * @return `true` if a HollowSoldier should be spawned, `false` otherwise.
     */
    @Override
    public boolean doesSpawn() {
        // Using Utility to determine if spawning occurs
        return Utility.getRandomEventOccurs(spawnChance);
    }

    /**
     * Setter for the spawner's spawn chance to allow possible modification.
     * (eg. due to weather effects)
     *
     * @param spawnChance Spawn chance
     */
    @Override
    public void setSpawnChance(int spawnChance) {
        this.spawnChance = spawnChance;
    }
}
