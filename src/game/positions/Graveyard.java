package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.spawners.Spawner;

/**
 * A special type of ground representing a graveyard.
 * This ground can spawn actors from a Spawner and restricts actor movement.
 * Created By:
 * @author Fahad Assadi
 */
public class Graveyard extends Ground {
    // Default display character for the Graveyard ground
    private static final char DEFAULT_DISPLAY_CHAR = 'n';
    // The spawner used to generate actors in the graveyard
    private final Spawner spawner;

    /**
     * Constructor for the Graveyard ground.
     *
     * @param spawner The Spawner responsible for generating actors in the graveyard.
     */
    public Graveyard(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR);
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
