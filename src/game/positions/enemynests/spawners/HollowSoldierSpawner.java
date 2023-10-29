package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.HollowSoldier;

/**
 * A spawner for creating instances of the HollowSoldier enemy actor.
 * It implements the Spawner interface and defines a spawning chance.
 * Created By:
 * @author Fahad Assadi
 */
public class HollowSoldierSpawner extends Spawner{
    // The chance of spawning a HollowSoldier (out of 100)
    private static final int DEFAULT_SPAWN_CHANCE = 10;

    /**
     * Creates a new instance of the HollowSoldierSpawner with the default spawn chance.
     * Constructor method that calls the parent class's constructor (Spawner)
     */
    public HollowSoldierSpawner() {
        super(DEFAULT_SPAWN_CHANCE);
    }

    /**
     * Spawn a new instance of the HollowSoldier enemy actor.
     *
     * @return A new instance of the HollowSoldier actor.
     */
    @Override
    public Actor spawnActor() {
        return new HollowSoldier();
    }
}
