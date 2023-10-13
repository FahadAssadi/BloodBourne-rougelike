package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.LivingBranch;

/**
 * A spawner for creating instances of the LivingBranch enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 */
public class LivingBranchSpawner extends Spawner{
    private static final int DEFAULT_SPAWN_CHANCE = 90;

    public LivingBranchSpawner() {
        super(DEFAULT_SPAWN_CHANCE);
    }

    /**
     * Spawn a new instance of the RedWolf enemy actor.
     *
     * @return A new instance of the RedWolf actor.
     */
    @Override
    public Actor spawnActor() {
        return new LivingBranch();
    }
}

