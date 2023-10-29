package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.WanderingUndead;

/**
 * A spawner for creating instances of the WanderingUndead enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 * Created By:
 * @author Fahad Assadi
 */
public class WanderingUndeadSpawner extends Spawner{
    // The chance of spawning a WanderingUndead (out of 100)
    private static final int DEFAULT_SPAWN_CHANCE = 25;

    /**
     * Creates a new instance of the WanderingUndeadSpawner with the default spawn chance.
     * Constructor method that calls the parent class's constructor (Spawner)
     */
    public WanderingUndeadSpawner() {
        super(DEFAULT_SPAWN_CHANCE);
    }

    /**
     * Spawn a new instance of the WanderingUndead enemy actor.
     *
     * @return A new instance of the WanderingUndead actor.
     */
    @Override
    public Actor spawnActor() {
        return new WanderingUndead();
    }

}
