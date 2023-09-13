package game.artifacts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;

/**
 * A specific item representing an Old Key in the game.
 */
public class OldKey extends Item {
    private static final String DEFAULT_NAME = "Old Key";
    private static final char DEFAULT_DISPLAY_CHAR = '-';
    private static final boolean DEFAULT_PORTABILITY_STATUS = true;

    /**
     * Constructor for the OldKey class.
     */
    public OldKey() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_PORTABILITY_STATUS);
    }

    /**
     * Override the tick method to add the POSSESS_KEY capability to the actor when the key is in the actor's inventory.
     *
     * @param currentLocation The location of the item.
     * @param actor           The actor holding the item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        actor.addCapability(Status.POSSESS_KEY);
    }
}
