package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Contract for objects that are responsible for spawning actors in a game.
 * Created By:
 * @author Fahad Assadi
 */
public interface Spawner {

    /**
     * Spawn an actor.
     *
     * @return The spawned actor.
     */
    Actor spawnActor();

    /**
     * Check if the spawner is currently set to spawn actors.
     *
     * @return `true` if the spawner should spawn an actor, `false` otherwise.
     */
    boolean doesSpawn();

    /**
     * Setter for the spawner's spawn chance to allow possible modification.
     * (eg. due to weather effects)
     *
     * @param spawnChance Spawn chance
     */
    void setSpawnChance(int spawnChance);
}
