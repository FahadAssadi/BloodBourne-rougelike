package game.positions.EnemyNest.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.WanderingUndead;
import game.misc.Utility;

/**
 * A spawner for creating instances of the WanderingUndead enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 * Created By:
 * @author Fahad Assadi
 */
public class WanderingUndeadSpawner implements Spawner{
    // The chance of spawning a WanderingUndead (out of 100)
    private static final int SPAWN_CHANCE = 25;

    /**
     * Spawn a new instance of the WanderingUndead enemy actor.
     *
     * @return A new instance of the WanderingUndead actor.
     */
    @Override
    public Actor spawnActor() {
        return new WanderingUndead();
    }

    /**
     * Check if the spawner should spawn a WanderingUndead.
     * This method uses a random event utility to determine the outcome based on SPAWN_CHANCE.
     *
     * @return `true` if a WanderingUndead should be spawned, `false` otherwise.
     */
    @Override
    public boolean doesSpawn() {
        // Using Utility to determine if spawning occurs
        return Utility.getRandomEventOccurs(SPAWN_CHANCE);
    }
}
