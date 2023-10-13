package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.misc.Utility;

/**
 * Contract for objects that are responsible for spawning actors in a game.
 * Created By:
 * @author Fahad Assadi
 */
public abstract class Spawner {
    private int spawnChance;

    public Spawner(int spawnChance){
        this.spawnChance = spawnChance;
    }

    /**
     * Spawn an actor.
     *
     * @return The spawned actor.
     */
    public abstract Actor spawnActor();

    /**
     * Check if the spawner is currently set to spawn actors.
     *
     * @return `true` if the spawner should spawn an actor, `false` otherwise.
     */
    public boolean doesSpawn(){
        return Utility.getRandomEventOccurs(this.spawnChance);
    }

    /**
     * Setter for the spawner's spawn chance to allow possible modification.
     * (eg. due to weather effects)
     *
     * @param spawnChance Spawn chance
     */
    public void setSpawnChance(int spawnChance){
        this.spawnChance = spawnChance;
    }
}
