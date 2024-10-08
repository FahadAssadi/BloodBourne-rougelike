package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.ForestKeeper;

/**
 * A spawner for creating instances of the ForestKeeper enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 */
public class ForestKeeperSpawner extends Spawner {
    // The chance of spawning a ForestKeeper (out of 100)
    private static final int DEFAULT_SPAWN_CHANCE = 15;

    /**
     * Creates a new instance of the ForestKeeperSpawner with the default spawn chance.
     * Constructor method that calls the parent class's constructor (Spawner)
     */
    public ForestKeeperSpawner() {
        super(DEFAULT_SPAWN_CHANCE);
    }

    /**
     * Spawn a new instance of the ForestKeeper enemy actor.
     *
     * @return A new instance of the HollowSoldier actor.
     */
    @Override
    public Actor spawnActor() {
        return new ForestKeeper();
    }
}

