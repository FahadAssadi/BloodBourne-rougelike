package game.positions.EnemyNest.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.RedWolf;
import game.misc.Utility;

public class RedWolfSpawner implements Spawner{
    private static final int SPAWN_CHANCE = 30;

    /**
     * Spawn a new instance of the RedWolf enemy actor.
     *
     * @return A new instance of the RedWolf actor.
     */
    @Override
    public Actor spawnActor() {
        return new RedWolf();
    }

    /**
     * Check if the spawner should spawn a RedWolf.
     * This method uses a random event utility to determine the outcome based on SPAWN_CHANCE.
     *
     * @return `true` if a HollowSolider should be spawned, `false` otherwise.
     */
    @Override
    public boolean doesSpawn() {
        return Utility.getRandomEventOccurs(SPAWN_CHANCE);
    }
}
