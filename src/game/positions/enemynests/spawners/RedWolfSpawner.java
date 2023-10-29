package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.RedWolf;

/**
 * A spawner for creating instances of the RedWolf enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 */
public class RedWolfSpawner extends Spawner {
    // The chance of spawning a RedWolf (out of 100)
    private static final int DEFAULT_SPAWN_CHANCE = 30;

    /**
     * Creates a new instance of the RedWoldSpawner with the default spawn chance.
     * Constructor method that calls the parent class's constructor (Spawner)
     */
    public RedWolfSpawner() {
        super(DEFAULT_SPAWN_CHANCE);
    }

    /**
     * Spawn a new instance of the RedWolf enemy actor.
     *
     * @return A new instance of the RedWolf actor.
     */
    @Override
    public Actor spawnActor() {
        return new RedWolf();
    }
}
