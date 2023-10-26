package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * A special type of ground representing a void or empty space.
 * Actors standing on this ground become unconscious.
 * Created by:
 * Fahad Assadi
 */
public class Void extends Ground {
    // Default display character for the Void ground
    private static final char DEFAULT_DISPLAY_CHAR = '+';

    /**
     * Constructor for the Void ground.
     * Initializes the ground with the default display character.
     */
    public Void() {
        super(DEFAULT_DISPLAY_CHAR);
    }

    /**
     * This method is called during each game turn (tick) for the location.
     * It checks if an actor is on this Void ground and makes them unconscious.
     *
     * @param location The location where this Void ground is situated.
     */
    @Override
    public void tick(Location location) {
        Actor actor = location.getActor();

        // Check if an actor is present on this ground
        if (actor != null && !actor.hasCapability(Ability.VOID_PROOF)){
            // Make the actor unconscious by calling the unconscious method
            actor.unconscious(location.map());
        }
    }
}
