package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.HollowSolider;
import game.misc.Utility;

/**
 * A spawner for creating instances of the HollowSolider enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 * Created By:
 * @author Fahad Assadi
 */
public class HollowSoliderSpawner implements Spawner{
    // The chance of spawning a HollowSolider (out of 100)
    private static final int SPAWN_CHANCE = 10;

    /**
     * Spawn a new instance of the HollowSolider enemy actor.
     *
     * @return A new instance of the HollowSolider actor.
     */
    @Override
    public Actor spawnActor() {
        return new HollowSolider();
    }

    /**
     * Check if the spawner should spawn a HollowSolider.
     * This method uses a random event utility to determine the outcome based on SPAWN_CHANCE.
     *
     * @return `true` if a HollowSolider should be spawned, `false` otherwise.
     */
    @Override
    public boolean doesSpawn() {
        // Using Utility to determine if spawning occurs
        return Utility.getRandomEventOccurs(SPAWN_CHANCE);
    }
}
