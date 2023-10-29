package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.LivingBranch;

/**
 * A spawner for creating instances of the LivingBranch enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 */
public class LivingBranchSpawner extends Spawner{
    private static final int DEFAULT_SPAWN_CHANCE = 90;

    /**
     * Creates a new instance of the LivingBranchSpawner with the default spawn chance.
     * Constructor method that calls the parent class's constructor (Spawner)
     */
    public LivingBranchSpawner() {
        super(DEFAULT_SPAWN_CHANCE);
    }

    /**
     * Spawn a new instance of the LivingBranch enemy actor.
     *
     * @return A new instance of the LivingBranch actor.
     */
    @Override
    public Actor spawnActor() {
        return new LivingBranch();
    }
}

