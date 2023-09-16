package game.positions.EnemyNest;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.EnemyNest.spawners.Spawner;

public abstract class EnemyNest extends Ground {
    private final Spawner spawner;

    public EnemyNest(char displayChar, Spawner spawner) {
        super(displayChar);
        this.spawner = spawner;
    }

    /**
     * This method is called during each game turn (tick) for the location.
     * If the spawner is set to spawn, it attempts to spawn actors from the Spawner
     * and places them in available exits.
     *
     * @param location The location where this Graveyard ground is situated.
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
