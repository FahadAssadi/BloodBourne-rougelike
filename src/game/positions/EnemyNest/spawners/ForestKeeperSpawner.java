package game.positions.EnemyNest.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.ForestKeeper;
import game.misc.Utility;

public class ForestKeeperSpawner implements Spawner {
    private static final int DEFAULT_SPAWN_CHANCE = 15;
    private int spawnChance = DEFAULT_SPAWN_CHANCE;

    /**
     * Spawn a new instance of the ForestKeeper enemy actor.
     *
     * @return A new instance of the HollowSolider actor.
     */
    @Override
    public Actor spawnActor() {
        return new ForestKeeper();
    }

    /**
     * Check if the spawner should spawn a ForestKeeper.
     * This method uses a random event utility to determine the outcome based on SPAWN_CHANCE.
     *
     * @return `true` if a HollowSolider should be spawned, `false` otherwise.
     */
    @Override
    public boolean doesSpawn() {
        return Utility.getRandomEventOccurs(spawnChance);
    }

    public void setSpawnChance(int spawnChance) {
        this.spawnChance = spawnChance;
    }
}

