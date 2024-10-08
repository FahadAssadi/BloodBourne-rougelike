package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.EldentreeGuardian;

/**
 * A spawner for creating instances of the EldentreeGuardian enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 *
 */
public class EldentreeGuardianSpawner extends Spawner{
    private static final int DEFAULT_SPAWN_CHANCE = 20;

    /**
     * Creates a new instance of the EldentreeGuardianSpawner with the default spawn chance.
     * Constructor method that calls the parent class's constructor (Spawner)
     */
    public EldentreeGuardianSpawner() {
        super(DEFAULT_SPAWN_CHANCE);
    }

    @Override
    public Actor spawnActor() {
        return new EldentreeGuardian();
    }
}
