package game.artifacts.vials;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface for consumable items in the game.
 */
public interface Consumable {
    /**
     * Define the behavior of consuming the item by an actor.
     *
     * @param actor The actor consuming the item.
     */
    void consume(Actor actor);
    /**
     * Define the allowable actions related to the consumable item for the owner actor.
     *
     * @param owner The actor who owns the consumable item.
     * @return An ActionList containing allowable actions for the owner actor.
     */
    ActionList allowableActions(Actor owner);
}
