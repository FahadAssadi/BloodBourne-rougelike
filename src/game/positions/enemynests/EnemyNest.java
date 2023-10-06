package game.positions.enemynests;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.enemynests.spawners.Spawner;

/**
 * A class that represents a Ground and acts as a wrapper for an Enemy Spawner,
 * and delegates the spawning of the spawners.
 */
public abstract class EnemyNest extends Ground {
    /*
    The Enemy Spawner that this enemy nest/ground represents
     */
    protected final Spawner spawner;

    /**
     * Constrctor for the EnemyNest
     * @param displayChar Display character of the ground
     * @param spawner The Enemy Spawner that this enemy nest/ground represents
     */
    public EnemyNest(char displayChar, Spawner spawner) {
        super(displayChar);
        this.spawner = spawner;
    }

    /**
     * This method is called during each game turn (tick) for the location.
     * If the spawner is set to spawn, it attempts to spawn actors from the Spawner
     * and places them in available exits.
     *
     * @param location The location where the EnemyNest Ground is situated.
     */
    @Override
    public void tick(Location location) {
        if (this.spawner.doesSpawn()){
            for (Exit exit : location.getExits()) {
                Location destination = exit.getDestination();

                Actor actor = spawner.spawnActor();

                if (destination.canActorEnter(actor)) {
                    destination.addActor(actor);
                    break;
                }
            }
        }
    }

    /**
     * Determines whether an actor can enter this Graveyard ground.
     * In this case, actors are not allowed to enter the graveyard.
     *
     * @param actor The actor trying to enter
     * @return False, indicating that actors cannot enter the graveyard.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
